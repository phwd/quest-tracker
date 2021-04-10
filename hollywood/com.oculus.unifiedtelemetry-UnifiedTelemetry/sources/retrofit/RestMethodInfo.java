package retrofit;

import X.AnonymousClass06;
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
        return new IllegalArgumentException(AnonymousClass06.A07(this.method.getDeclaringClass().getSimpleName(), ".", this.method.getName(), ": ", str));
    }

    private void parsePath(String str) {
        Object[] objArr;
        String str2;
        int length;
        String str3;
        if (str == null || (length = str.length()) == 0 || str.charAt(0) != '/') {
            objArr = new Object[]{str};
            str2 = "URL path \"%s\" must start with '/'.";
        } else {
            String str4 = null;
            int indexOf = str.indexOf(63);
            if (indexOf == -1 || indexOf >= length - 1) {
                str3 = str;
            } else {
                str3 = str.substring(0, indexOf);
                str4 = str.substring(indexOf + 1);
                if (PARAM_URL_REGEX.matcher(str4).find()) {
                    objArr = new Object[]{str4};
                    str2 = "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.";
                }
            }
            Set<String> parsePathParameters = parsePathParameters(str);
            this.requestUrl = str3;
            this.requestUrlParamNames = parsePathParameters;
            this.requestQuery = str4;
            return;
        }
        throw methodError(str2, objArr);
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

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ae, code lost:
        throw methodError("Only one encoding annotation is allowed.", new java.lang.Object[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseMethodAnnotations() {
        /*
        // Method dump skipped, instructions count: 235
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit.RestMethodInfo.parseMethodAnnotations():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0153, code lost:
        throw methodError(r0, r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x010b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseParameters() {
        /*
        // Method dump skipped, instructions count: 343
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
        Object[] objArr;
        String str;
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
            objArr = new Object[0];
            str = "Must have return type or Callback as last argument, not both.";
        } else if (z) {
            Type supertype = Types.getSupertype(type, Types.getRawType(type), Callback.class);
            if (supertype instanceof ParameterizedType) {
                this.responseObjectType = getParameterUpperBound((ParameterizedType) supertype);
                return ResponseType.VOID;
            }
            objArr = new Object[0];
            str = "Last parameter must be of type Callback<X> or Callback<? super X>.";
        } else {
            objArr = new Object[0];
            str = "Must have either a return type or Callback as last argument.";
        }
        throw methodError(str, objArr);
    }

    private void validatePathName(int i, String str) {
        Object[] objArr;
        String str2;
        if (!PARAM_NAME_REGEX.matcher(str).matches()) {
            objArr = new Object[]{PARAM_URL_REGEX.pattern(), str};
            str2 = "@Path parameter name must match %s. Found: %s";
        } else if (!this.requestUrlParamNames.contains(str)) {
            objArr = new Object[]{this.requestUrl, str};
            str2 = "URL \"%s\" does not contain \"{%s}\".";
        } else {
            return;
        }
        throw parameterError(i, str2, objArr);
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
