package retrofit;

import X.AnonymousClass006;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import retrofit.RequestInterceptor;
import retrofit.RestMethodInfo;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.converter.Converter;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.EncodedQuery;
import retrofit.http.EncodedQueryMap;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.mime.FormUrlEncodedTypedOutput;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedOutput;
import retrofit.mime.TypedString;

public final class RequestBuilder implements RequestInterceptor.RequestFacade {
    public final String apiUrl;
    public TypedOutput body;
    public String contentTypeHeader;
    public final Converter converter;
    public final FormUrlEncodedTypedOutput formBody;
    public List<Header> headers;
    public final boolean isObservable;
    public final boolean isSynchronous;
    public final MultipartTypedOutput multipartBody;
    public final Annotation[] paramAnnotations;
    public StringBuilder queryParams;
    public String relativeUrl;
    public final String requestMethod;

    public static class MimeOverridingTypedOutput implements TypedOutput {
        public final TypedOutput delegate;
        public final String mimeType;

        @Override // retrofit.mime.TypedOutput
        public String fileName() {
            return this.delegate.fileName();
        }

        @Override // retrofit.mime.TypedOutput
        public long length() {
            return this.delegate.length();
        }

        @Override // retrofit.mime.TypedOutput
        public String mimeType() {
            return this.mimeType;
        }

        @Override // retrofit.mime.TypedOutput
        public void writeTo(OutputStream outputStream) throws IOException {
            this.delegate.writeTo(outputStream);
        }

        public MimeOverridingTypedOutput(TypedOutput typedOutput, String str) {
            this.delegate = typedOutput;
            this.mimeType = str;
        }
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedPathParam(String str, String str2) {
        addPathParam(str, str2, false);
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedQueryParam(String str, String str2) {
        addQueryParam(str, str2, false, false);
    }

    /* renamed from: retrofit.RequestBuilder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$retrofit$RestMethodInfo$RequestType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                retrofit.RestMethodInfo$RequestType[] r0 = retrofit.RestMethodInfo.RequestType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                retrofit.RequestBuilder.AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType = r2
                retrofit.RestMethodInfo$RequestType r0 = retrofit.RestMethodInfo.RequestType.FORM_URL_ENCODED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                retrofit.RestMethodInfo$RequestType r0 = retrofit.RestMethodInfo.RequestType.MULTIPART     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                retrofit.RestMethodInfo$RequestType r0 = retrofit.RestMethodInfo.RequestType.SIMPLE     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit.RequestBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addHeader(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Header name must not be null.");
        } else if ("Content-Type".equalsIgnoreCase(str)) {
            this.contentTypeHeader = str2;
        } else {
            List list = this.headers;
            if (list == null) {
                list = new ArrayList(2);
                this.headers = list;
            }
            list.add(new Header(str, str2));
        }
    }

    public Request build() throws UnsupportedEncodingException {
        MultipartTypedOutput multipartTypedOutput = this.multipartBody;
        if (multipartTypedOutput == null || multipartTypedOutput.mimeParts.size() != 0) {
            String str = this.apiUrl;
            StringBuilder sb = new StringBuilder(str);
            if (str.endsWith("/")) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(this.relativeUrl);
            StringBuilder sb2 = this.queryParams;
            if (sb2 != null) {
                sb.append((CharSequence) sb2);
            }
            TypedOutput typedOutput = this.body;
            List<Header> list = this.headers;
            String str2 = this.contentTypeHeader;
            if (str2 != null) {
                if (typedOutput != null) {
                    typedOutput = new MimeOverridingTypedOutput(typedOutput, str2);
                } else {
                    Header header = new Header("Content-Type", str2);
                    if (list == null) {
                        list = Collections.singletonList(header);
                    } else {
                        list.add(header);
                    }
                }
            }
            return new Request(this.requestMethod, sb.toString(), list, typedOutput);
        }
        throw new IllegalStateException("Multipart requests must contain at least one part.");
    }

    public void setArguments(Object[] objArr) {
        String str;
        StringBuilder sb;
        String str2;
        String str3;
        TypedOutput body2;
        MultipartTypedOutput multipartTypedOutput;
        TypedOutput body3;
        MultipartTypedOutput multipartTypedOutput2;
        TypedOutput body4;
        if (objArr != null) {
            int length = objArr.length;
            if (!this.isSynchronous && !this.isObservable) {
                length--;
            }
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                Annotation annotation = this.paramAnnotations[i];
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType == Path.class) {
                    Path path = (Path) annotation;
                    str2 = path.value();
                    if (obj != null) {
                        addPathParam(str2, obj.toString(), path.encode());
                    } else {
                        sb = new StringBuilder();
                        sb.append("Path parameter \"");
                        sb.append(str2);
                        sb.append("\" value must not be null.");
                        str = sb.toString();
                        throw new IllegalArgumentException(str);
                    }
                } else if (annotationType == EncodedPath.class) {
                    str2 = ((EncodedPath) annotation).value();
                    if (obj != null) {
                        addPathParam(str2, obj.toString(), false);
                    } else {
                        sb = new StringBuilder();
                        sb.append("Path parameter \"");
                        sb.append(str2);
                        sb.append("\" value must not be null.");
                        str = sb.toString();
                        throw new IllegalArgumentException(str);
                    }
                } else if (annotationType == Query.class) {
                    if (obj != null) {
                        Query query = (Query) annotation;
                        addQueryParam(query.value(), obj, query.encodeName(), query.encodeValue());
                    }
                } else if (annotationType == EncodedQuery.class) {
                    if (obj != null) {
                        addQueryParam(((EncodedQuery) annotation).value(), obj, false, false);
                    }
                } else if (annotationType == QueryMap.class) {
                    if (obj != null) {
                        QueryMap queryMap = (QueryMap) annotation;
                        addQueryParamMap(i, (Map) obj, queryMap.encodeNames(), queryMap.encodeValues());
                    }
                } else if (annotationType == EncodedQueryMap.class) {
                    if (obj != null) {
                        addQueryParamMap(i, (Map) obj, false, false);
                    }
                } else if (annotationType == retrofit.http.Header.class) {
                    if (obj != null) {
                        String value = ((retrofit.http.Header) annotation).value();
                        if (obj instanceof Iterable) {
                            for (Object obj2 : (Iterable) obj) {
                                if (obj2 != null) {
                                    addHeader(value, obj2.toString());
                                }
                            }
                        } else if (obj.getClass().isArray()) {
                            int length2 = Array.getLength(obj);
                            for (int i2 = 0; i2 < length2; i2++) {
                                Object obj3 = Array.get(obj, i2);
                                if (obj3 != null) {
                                    addHeader(value, obj3.toString());
                                }
                            }
                        } else {
                            addHeader(value, obj.toString());
                        }
                    }
                } else if (annotationType != Field.class) {
                    if (annotationType == FieldMap.class) {
                        if (obj != null) {
                            FieldMap fieldMap = (FieldMap) annotation;
                            boolean encodeNames = fieldMap.encodeNames();
                            boolean encodeValues = fieldMap.encodeValues();
                            for (Map.Entry entry : ((Map) obj).entrySet()) {
                                Object key = entry.getKey();
                                if (key != null) {
                                    Object value2 = entry.getValue();
                                    if (value2 != null) {
                                        this.formBody.addField(key.toString(), encodeNames, value2.toString(), encodeValues);
                                    }
                                } else {
                                    sb = new StringBuilder();
                                    sb.append("Parameter #");
                                    sb.append(i + 1);
                                    str3 = " field map contained null key.";
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    } else if (annotationType == Part.class) {
                        if (obj != null) {
                            Part part = (Part) annotation;
                            String value3 = part.value();
                            String encoding = part.encoding();
                            if (obj instanceof TypedOutput) {
                                multipartTypedOutput2 = this.multipartBody;
                                body4 = (TypedOutput) obj;
                            } else if (obj instanceof String) {
                                this.multipartBody.addPart(value3, encoding, new TypedString((String) obj));
                            } else {
                                multipartTypedOutput2 = this.multipartBody;
                                body4 = this.converter.toBody(obj);
                            }
                            multipartTypedOutput2.addPart(value3, encoding, body4);
                        }
                    } else if (annotationType == PartMap.class) {
                        if (obj != null) {
                            String encoding2 = ((PartMap) annotation).encoding();
                            for (Map.Entry entry2 : ((Map) obj).entrySet()) {
                                Object key2 = entry2.getKey();
                                if (key2 != null) {
                                    String obj4 = key2.toString();
                                    Object value4 = entry2.getValue();
                                    if (value4 != null) {
                                        if (value4 instanceof TypedOutput) {
                                            multipartTypedOutput = this.multipartBody;
                                            body3 = (TypedOutput) value4;
                                        } else if (value4 instanceof String) {
                                            this.multipartBody.addPart(obj4, encoding2, new TypedString((String) value4));
                                        } else {
                                            multipartTypedOutput = this.multipartBody;
                                            body3 = this.converter.toBody(value4);
                                        }
                                        multipartTypedOutput.addPart(obj4, encoding2, body3);
                                    }
                                } else {
                                    sb = new StringBuilder();
                                    sb.append("Parameter #");
                                    sb.append(i + 1);
                                    str3 = " part map contained null key.";
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    } else if (annotationType != Body.class) {
                        sb = new StringBuilder("Unknown annotation: ");
                        str3 = annotationType.getCanonicalName();
                    } else if (obj != null) {
                        if (obj instanceof TypedOutput) {
                            body2 = (TypedOutput) obj;
                        } else {
                            body2 = this.converter.toBody(obj);
                        }
                        this.body = body2;
                    } else {
                        str = "Body parameter value must not be null.";
                        throw new IllegalArgumentException(str);
                    }
                    sb.append(str3);
                    str = sb.toString();
                    throw new IllegalArgumentException(str);
                } else if (obj != null) {
                    Field field = (Field) annotation;
                    String value5 = field.value();
                    boolean encodeName = field.encodeName();
                    boolean encodeValue = field.encodeValue();
                    if (obj instanceof Iterable) {
                        for (Object obj5 : (Iterable) obj) {
                            if (obj5 != null) {
                                this.formBody.addField(value5, encodeName, obj5.toString(), encodeValue);
                            }
                        }
                    } else if (obj.getClass().isArray()) {
                        int length3 = Array.getLength(obj);
                        for (int i3 = 0; i3 < length3; i3++) {
                            Object obj6 = Array.get(obj, i3);
                            if (obj6 != null) {
                                this.formBody.addField(value5, encodeName, obj6.toString(), encodeValue);
                            }
                        }
                    } else {
                        this.formBody.addField(value5, encodeName, obj.toString(), encodeValue);
                    }
                }
            }
        }
    }

    public RequestBuilder(String str, RestMethodInfo restMethodInfo, Converter converter2) {
        this.apiUrl = str;
        this.converter = converter2;
        this.paramAnnotations = restMethodInfo.requestParamAnnotations;
        this.requestMethod = restMethodInfo.requestMethod;
        this.isSynchronous = restMethodInfo.isSynchronous;
        this.isObservable = restMethodInfo.isObservable;
        List<Header> list = restMethodInfo.headers;
        if (list != null) {
            this.headers = new ArrayList(list);
        }
        this.contentTypeHeader = restMethodInfo.contentTypeHeader;
        this.relativeUrl = restMethodInfo.requestUrl;
        String str2 = restMethodInfo.requestQuery;
        if (str2 != null) {
            StringBuilder sb = new StringBuilder("?");
            sb.append(str2);
            this.queryParams = sb;
        }
        RestMethodInfo.RequestType requestType = restMethodInfo.requestType;
        switch (requestType.ordinal()) {
            case 0:
                this.formBody = null;
                this.multipartBody = null;
                return;
            case 1:
                this.formBody = null;
                MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
                this.multipartBody = multipartTypedOutput;
                this.body = multipartTypedOutput;
                return;
            case 2:
                FormUrlEncodedTypedOutput formUrlEncodedTypedOutput = new FormUrlEncodedTypedOutput();
                this.formBody = formUrlEncodedTypedOutput;
                this.multipartBody = null;
                this.body = formUrlEncodedTypedOutput;
                return;
            default:
                throw new IllegalArgumentException("Unknown request type: " + requestType);
        }
    }

    private void addQueryParamMap(int i, Map<?, ?> map, boolean z, boolean z2) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            if (key != null) {
                Object value = entry.getValue();
                if (value != null) {
                    addQueryParam(key.toString(), value.toString(), z, z2);
                }
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A02("Parameter #", i + 1, " query map contained null key."));
            }
        }
    }

    private void addPathParam(String str, String str2, boolean z) {
        String str3;
        if (str == null) {
            str3 = "Path replacement name must not be null.";
        } else if (str2 == null) {
            str3 = AnonymousClass006.A07("Path replacement \"", str, "\" value must not be null.");
        } else if (z) {
            try {
                this.relativeUrl = this.relativeUrl.replace(AnonymousClass006.A07("{", str, "}"), URLEncoder.encode(str2, "UTF-8").replace("+", "%20"));
                return;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(AnonymousClass006.A08("Unable to convert path parameter \"", str, "\" value to UTF-8:", str2), e);
            }
        } else {
            this.relativeUrl = this.relativeUrl.replace(AnonymousClass006.A07("{", str, "}"), str2);
            return;
        }
        throw new IllegalArgumentException(str3);
    }

    private void addQueryParam(String str, Object obj, boolean z, boolean z2) {
        if (obj instanceof Iterable) {
            for (Object obj2 : (Iterable) obj) {
                if (obj2 != null) {
                    addQueryParam(str, obj2.toString(), z, z2);
                }
            }
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                Object obj3 = Array.get(obj, i);
                if (obj3 != null) {
                    addQueryParam(str, obj3.toString(), z, z2);
                }
            }
        } else {
            addQueryParam(str, obj.toString(), z, z2);
        }
    }

    private void addQueryParam(String str, String str2, boolean z, boolean z2) {
        String str3;
        if (str == null) {
            str3 = "Query param name must not be null.";
        } else if (str2 != null) {
            try {
                StringBuilder sb = this.queryParams;
                if (sb == null) {
                    sb = new StringBuilder();
                    this.queryParams = sb;
                }
                char c = '?';
                if (sb.length() > 0) {
                    c = '&';
                }
                sb.append(c);
                if (z) {
                    str = URLEncoder.encode(str, "UTF-8");
                }
                if (z2) {
                    str2 = URLEncoder.encode(str2, "UTF-8");
                }
                sb.append(str);
                sb.append('=');
                sb.append(str2);
                return;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(AnonymousClass006.A08("Unable to convert query parameter \"", str, "\" value to UTF-8: ", str2), e);
            }
        } else {
            str3 = AnonymousClass006.A07("Query param \"", str, "\" value must not be null.");
        }
        throw new IllegalArgumentException(str3);
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addPathParam(String str, String str2) {
        addPathParam(str, str2, true);
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addQueryParam(String str, String str2) {
        addQueryParam(str, str2, false, true);
    }
}
