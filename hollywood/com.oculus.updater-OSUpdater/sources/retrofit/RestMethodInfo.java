package retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.RestMethod;
import retrofit.http.Streaming;
import rx.Observable;

/* access modifiers changed from: package-private */
public final class RestMethodInfo {
    private static final Pattern PARAM_NAME_REGEX = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    String contentTypeHeader;
    List<Header> headers;
    final boolean isObservable;
    boolean isStreaming;
    final boolean isSynchronous;
    boolean loaded = false;
    final Method method;
    boolean requestHasBody;
    String requestMethod;
    Annotation[] requestParamAnnotations;
    String requestQuery;
    RequestType requestType = RequestType.SIMPLE;
    String requestUrl;
    Set<String> requestUrlParamNames;
    Type responseObjectType;
    final ResponseType responseType;

    /* access modifiers changed from: package-private */
    public enum RequestType {
        SIMPLE,
        MULTIPART,
        FORM_URL_ENCODED
    }

    /* access modifiers changed from: private */
    public enum ResponseType {
        VOID,
        OBSERVABLE,
        OBJECT
    }

    RestMethodInfo(Method method2) {
        boolean z = false;
        this.method = method2;
        this.responseType = parseResponseType();
        this.isSynchronous = this.responseType == ResponseType.OBJECT;
        this.isObservable = this.responseType == ResponseType.OBSERVABLE ? true : z;
    }

    private RuntimeException methodError(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return new IllegalArgumentException(this.method.getDeclaringClass().getSimpleName() + "." + this.method.getName() + ": " + str);
    }

    private RuntimeException parameterError(int i, String str, Object... objArr) {
        return methodError(str + " (parameter #" + (i + 1) + ")", objArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void init() {
        if (!this.loaded) {
            parseMethodAnnotations();
            parseParameters();
            this.loaded = true;
        }
    }

    private void parseMethodAnnotations() {
        Annotation[] annotations = this.method.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            RestMethod restMethod = null;
            Annotation[] annotations2 = annotationType.getAnnotations();
            int length = annotations2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Annotation annotation2 = annotations2[i];
                if (RestMethod.class == annotation2.annotationType()) {
                    restMethod = (RestMethod) annotation2;
                    break;
                }
                i++;
            }
            if (restMethod != null) {
                String str = this.requestMethod;
                if (str == null) {
                    try {
                        parsePath((String) annotationType.getMethod("value", new Class[0]).invoke(annotation, new Object[0]));
                        this.requestMethod = restMethod.value();
                        this.requestHasBody = restMethod.hasBody();
                    } catch (Exception unused) {
                        throw methodError("Failed to extract String 'value' from @%s annotation.", annotationType.getSimpleName());
                    }
                } else {
                    throw methodError("Only one HTTP method is allowed. Found: %s and %s.", str, restMethod.value());
                }
            } else if (annotationType == Headers.class) {
                String[] value = ((Headers) annotation).value();
                if (value.length != 0) {
                    this.headers = parseHeaders(value);
                } else {
                    throw methodError("@Headers annotation is empty.", new Object[0]);
                }
            } else if (annotationType == Multipart.class) {
                if (this.requestType == RequestType.SIMPLE) {
                    this.requestType = RequestType.MULTIPART;
                } else {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
            } else if (annotationType == FormUrlEncoded.class) {
                if (this.requestType == RequestType.SIMPLE) {
                    this.requestType = RequestType.FORM_URL_ENCODED;
                } else {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
            } else if (annotationType != Streaming.class) {
                continue;
            } else if (this.responseObjectType == Response.class) {
                this.isStreaming = true;
            } else {
                throw methodError("Only methods having %s as data type are allowed to have @%s annotation.", Response.class.getSimpleName(), Streaming.class.getSimpleName());
            }
        }
        if (this.requestMethod == null) {
            throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        } else if (!this.requestHasBody) {
            if (this.requestType == RequestType.MULTIPART) {
                throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            } else if (this.requestType == RequestType.FORM_URL_ENCODED) {
                throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            }
        }
    }

    private void parsePath(String str) {
        String str2;
        String str3;
        if (str == null || str.length() == 0 || str.charAt(0) != '/') {
            throw methodError("URL path \"%s\" must start with '/'.", str);
        }
        int indexOf = str.indexOf(63);
        if (indexOf == -1 || indexOf >= str.length() - 1) {
            str2 = null;
            str3 = str;
        } else {
            str3 = str.substring(0, indexOf);
            str2 = str.substring(indexOf + 1);
            if (PARAM_URL_REGEX.matcher(str2).find()) {
                throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", str2);
            }
        }
        Set<String> parsePathParameters = parsePathParameters(str);
        this.requestUrl = str3;
        this.requestUrlParamNames = parsePathParameters;
        this.requestQuery = str2;
    }

    /* access modifiers changed from: package-private */
    public List<Header> parseHeaders(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int indexOf = str.indexOf(58);
            if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
            }
            String substring = str.substring(0, indexOf);
            String trim = str.substring(indexOf + 1).trim();
            if ("Content-Type".equalsIgnoreCase(substring)) {
                this.contentTypeHeader = trim;
            } else {
                arrayList.add(new Header(substring, trim));
            }
        }
        return arrayList;
    }

    private ResponseType parseResponseType() {
        Type type;
        Type genericReturnType = this.method.getGenericReturnType();
        Type[] genericParameterTypes = this.method.getGenericParameterTypes();
        Class cls = null;
        boolean z = true;
        if (genericParameterTypes.length > 0) {
            type = genericParameterTypes[genericParameterTypes.length - 1];
            Type rawType = type instanceof ParameterizedType ? ((ParameterizedType) type).getRawType() : type;
            if (rawType instanceof Class) {
                cls = (Class) rawType;
            }
        } else {
            type = null;
        }
        boolean z2 = genericReturnType != Void.TYPE;
        if (cls == null || !Callback.class.isAssignableFrom(cls)) {
            z = false;
        }
        if (z2 && z) {
            throw methodError("Must have return type or Callback as last argument, not both.", new Object[0]);
        } else if (!z2 && !z) {
            throw methodError("Must have either a return type or Callback as last argument.", new Object[0]);
        } else if (z2) {
            if (Platform.HAS_RX_JAVA) {
                Class<?> rawType2 = Types.getRawType(genericReturnType);
                if (RxSupport.isObservable(rawType2)) {
                    this.responseObjectType = getParameterUpperBound((ParameterizedType) RxSupport.getObservableType(genericReturnType, rawType2));
                    return ResponseType.OBSERVABLE;
                }
            }
            this.responseObjectType = genericReturnType;
            return ResponseType.OBJECT;
        } else {
            Type supertype = Types.getSupertype(type, Types.getRawType(type), Callback.class);
            if (supertype instanceof ParameterizedType) {
                this.responseObjectType = getParameterUpperBound((ParameterizedType) supertype);
                return ResponseType.VOID;
            }
            throw methodError("Last parameter must be of type Callback<X> or Callback<? super X>.", new Object[0]);
        }
    }

    private static Type getParameterUpperBound(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type = actualTypeArguments[i];
            if (type instanceof WildcardType) {
                actualTypeArguments[i] = ((WildcardType) type).getUpperBounds()[0];
            }
        }
        return actualTypeArguments[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x0127 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0124  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseParameters() {
        /*
        // Method dump skipped, instructions count: 440
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.RestMethodInfo.parseParameters():void");
    }

    private void validatePathName(int i, String str) {
        if (!PARAM_NAME_REGEX.matcher(str).matches()) {
            throw parameterError(i, "@Path parameter name must match %s. Found: %s", PARAM_URL_REGEX.pattern(), str);
        } else if (!this.requestUrlParamNames.contains(str)) {
            throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.requestUrl, str);
        }
    }

    static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    /* access modifiers changed from: private */
    public static final class RxSupport {
        private RxSupport() {
        }

        public static boolean isObservable(Class cls) {
            return cls == Observable.class;
        }

        public static Type getObservableType(Type type, Class cls) {
            return Types.getSupertype(type, cls, Observable.class);
        }
    }
}
