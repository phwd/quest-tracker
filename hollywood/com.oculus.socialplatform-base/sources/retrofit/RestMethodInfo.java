package retrofit;

import X.AnonymousClass006;
import com.facebook.acra.util.HttpRequestMultipart;
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

public final class RestMethodInfo {
    public static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    public static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    public static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    public String contentTypeHeader;
    public List<Header> headers;
    public final boolean isObservable;
    public boolean isStreaming;
    public final boolean isSynchronous;
    public boolean loaded = false;
    public final Method method;
    public boolean requestHasBody;
    public String requestMethod;
    public Annotation[] requestParamAnnotations;
    public String requestQuery;
    public RequestType requestType = RequestType.SIMPLE;
    public String requestUrl;
    public Set<String> requestUrlParamNames;
    public Type responseObjectType;
    public final ResponseType responseType;

    public enum RequestType {
        SIMPLE,
        MULTIPART,
        FORM_URL_ENCODED
    }

    public enum ResponseType {
        VOID,
        OBSERVABLE,
        OBJECT
    }

    public static final class RxSupport {
        public static Type getObservableType(Type type, Class cls) {
            return Types.getSupertype(type, cls, Observable.class);
        }

        public static boolean isObservable(Class cls) {
            if (cls == Observable.class) {
                return true;
            }
            return false;
        }
    }

    private RuntimeException methodError(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return new IllegalArgumentException(AnonymousClass006.A0C(this.method.getDeclaringClass().getSimpleName(), ".", this.method.getName(), ": ", str));
    }

    private void parsePath(String str) {
        int length;
        String str2;
        if (str == null || (length = str.length()) == 0 || str.charAt(0) != '/') {
            throw methodError("URL path \"%s\" must start with '/'.", str);
        }
        String str3 = null;
        int indexOf = str.indexOf(63);
        if (indexOf == -1 || indexOf >= length - 1) {
            str2 = str;
        } else {
            str2 = str.substring(0, indexOf);
            str3 = str.substring(indexOf + 1);
            if (PARAM_URL_REGEX.matcher(str3).find()) {
                throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", str3);
            }
        }
        Set<String> parsePathParameters = parsePathParameters(str);
        this.requestUrl = str2;
        this.requestUrlParamNames = parsePathParameters;
        this.requestQuery = str3;
    }

    public synchronized void init() {
        if (!this.loaded) {
            parseMethodAnnotations();
            parseParameters();
            this.loaded = true;
        }
    }

    private RuntimeException parameterError(int i, String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" (parameter #");
        sb.append(i + 1);
        sb.append(")");
        return methodError(sb.toString(), objArr);
    }

    private void parseMethodAnnotations() {
        RequestType requestType2;
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
            } else {
                if (annotationType == Multipart.class) {
                    if (this.requestType == RequestType.SIMPLE) {
                        requestType2 = RequestType.MULTIPART;
                    } else {
                        throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                    }
                } else if (annotationType == FormUrlEncoded.class) {
                    if (this.requestType == RequestType.SIMPLE) {
                        requestType2 = RequestType.FORM_URL_ENCODED;
                    } else {
                        throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                    }
                } else if (annotationType != Streaming.class) {
                    continue;
                } else if (this.responseObjectType == Response.class) {
                    this.isStreaming = true;
                } else {
                    throw methodError("Only methods having %s as data type are allowed to have @%s annotation.", "Response", "Streaming");
                }
                this.requestType = requestType2;
            }
        }
        if (this.requestMethod == null) {
            throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        } else if (!this.requestHasBody) {
            RequestType requestType3 = this.requestType;
            if (requestType3 == RequestType.MULTIPART) {
                throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            } else if (requestType3 == RequestType.FORM_URL_ENCODED) {
                throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x0108 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseParameters() {
        /*
        // Method dump skipped, instructions count: 392
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.RestMethodInfo.parseParameters():void");
    }

    public static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    private ResponseType parseResponseType() {
        Type type;
        Type genericReturnType = this.method.getGenericReturnType();
        Type[] genericParameterTypes = this.method.getGenericParameterTypes();
        int length = genericParameterTypes.length;
        Class cls = null;
        boolean z = true;
        if (length > 0) {
            type = genericParameterTypes[length - 1];
            Type type2 = type;
            if (type instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type2).getRawType();
            }
            if (type2 instanceof Class) {
                cls = (Class) type2;
            }
        } else {
            type = null;
        }
        boolean z2 = false;
        if (genericReturnType != Void.TYPE) {
            z2 = true;
        }
        if (cls == null || !Callback.class.isAssignableFrom(cls)) {
            z = false;
        }
        if (z2) {
            if (!z) {
                if (Platform.HAS_RX_JAVA) {
                    Class<?> rawType = Types.getRawType(genericReturnType);
                    if (RxSupport.isObservable(rawType)) {
                        this.responseObjectType = getParameterUpperBound((ParameterizedType) Types.getSupertype(genericReturnType, rawType, Observable.class));
                        return ResponseType.OBSERVABLE;
                    }
                }
                this.responseObjectType = genericReturnType;
                return ResponseType.OBJECT;
            }
            throw methodError("Must have return type or Callback as last argument, not both.", new Object[0]);
        } else if (z) {
            Type supertype = Types.getSupertype(type, Types.getRawType(type), Callback.class);
            if (supertype instanceof ParameterizedType) {
                this.responseObjectType = getParameterUpperBound((ParameterizedType) supertype);
                return ResponseType.VOID;
            }
            throw methodError("Last parameter must be of type Callback<X> or Callback<? super X>.", new Object[0]);
        } else {
            throw methodError("Must have either a return type or Callback as last argument.", new Object[0]);
        }
    }

    private void validatePathName(int i, String str) {
        if (!PARAM_NAME_REGEX.matcher(str).matches()) {
            throw parameterError(i, "@Path parameter name must match %s. Found: %s", PARAM_URL_REGEX.pattern(), str);
        } else if (!this.requestUrlParamNames.contains(str)) {
            throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.requestUrl, str);
        }
    }

    public List<Header> parseHeaders(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int indexOf = str.indexOf(58);
            if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
            }
            String substring = str.substring(0, indexOf);
            String trim = str.substring(indexOf + 1).trim();
            if (HttpRequestMultipart.CONTENT_TYPE.equalsIgnoreCase(substring)) {
                this.contentTypeHeader = trim;
            } else {
                arrayList.add(new Header(substring, trim));
            }
        }
        return arrayList;
    }

    public RestMethodInfo(Method method2) {
        boolean z = false;
        this.method = method2;
        ResponseType parseResponseType = parseResponseType();
        this.responseType = parseResponseType;
        this.isSynchronous = parseResponseType == ResponseType.OBJECT;
        this.isObservable = parseResponseType == ResponseType.OBSERVABLE ? true : z;
    }

    public static Type getParameterUpperBound(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type = actualTypeArguments[i];
            if (type instanceof WildcardType) {
                actualTypeArguments[i] = ((WildcardType) type).getUpperBounds()[0];
            }
        }
        return actualTypeArguments[0];
    }
}
