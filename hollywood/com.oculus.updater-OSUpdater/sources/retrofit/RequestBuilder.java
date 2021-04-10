package retrofit;

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

/* access modifiers changed from: package-private */
public final class RequestBuilder implements RequestInterceptor.RequestFacade {
    private final String apiUrl;
    private TypedOutput body;
    private String contentTypeHeader;
    private final Converter converter;
    private final FormUrlEncodedTypedOutput formBody;
    private List<Header> headers;
    private final boolean isObservable;
    private final boolean isSynchronous;
    private final MultipartTypedOutput multipartBody;
    private final Annotation[] paramAnnotations;
    private StringBuilder queryParams;
    private String relativeUrl;
    private final String requestMethod;

    RequestBuilder(String str, RestMethodInfo restMethodInfo, Converter converter2) {
        this.apiUrl = str;
        this.converter = converter2;
        this.paramAnnotations = restMethodInfo.requestParamAnnotations;
        this.requestMethod = restMethodInfo.requestMethod;
        this.isSynchronous = restMethodInfo.isSynchronous;
        this.isObservable = restMethodInfo.isObservable;
        if (restMethodInfo.headers != null) {
            this.headers = new ArrayList(restMethodInfo.headers);
        }
        this.contentTypeHeader = restMethodInfo.contentTypeHeader;
        this.relativeUrl = restMethodInfo.requestUrl;
        String str2 = restMethodInfo.requestQuery;
        if (str2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append('?');
            sb.append(str2);
            this.queryParams = sb;
        }
        int i = AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType[restMethodInfo.requestType.ordinal()];
        if (i == 1) {
            this.formBody = new FormUrlEncodedTypedOutput();
            this.multipartBody = null;
            this.body = this.formBody;
        } else if (i == 2) {
            this.formBody = null;
            this.multipartBody = new MultipartTypedOutput();
            this.body = this.multipartBody;
        } else if (i == 3) {
            this.formBody = null;
            this.multipartBody = null;
        } else {
            throw new IllegalArgumentException("Unknown request type: " + restMethodInfo.requestType);
        }
    }

    /* renamed from: retrofit.RequestBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$retrofit$RestMethodInfo$RequestType = new int[RestMethodInfo.RequestType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                retrofit.RestMethodInfo$RequestType[] r0 = retrofit.RestMethodInfo.RequestType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                retrofit.RequestBuilder.AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType = r0
                int[] r0 = retrofit.RequestBuilder.AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType     // Catch:{ NoSuchFieldError -> 0x0014 }
                retrofit.RestMethodInfo$RequestType r1 = retrofit.RestMethodInfo.RequestType.FORM_URL_ENCODED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = retrofit.RequestBuilder.AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType     // Catch:{ NoSuchFieldError -> 0x001f }
                retrofit.RestMethodInfo$RequestType r1 = retrofit.RestMethodInfo.RequestType.MULTIPART     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = retrofit.RequestBuilder.AnonymousClass1.$SwitchMap$retrofit$RestMethodInfo$RequestType     // Catch:{ NoSuchFieldError -> 0x002a }
                retrofit.RestMethodInfo$RequestType r1 = retrofit.RestMethodInfo.RequestType.SIMPLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
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

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addPathParam(String str, String str2) {
        addPathParam(str, str2, true);
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedPathParam(String str, String str2) {
        addPathParam(str, str2, false);
    }

    private void addPathParam(String str, String str2, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("Path replacement name must not be null.");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Path replacement \"" + str + "\" value must not be null.");
        } else if (z) {
            try {
                String replace = URLEncoder.encode(str2, "UTF-8").replace("+", "%20");
                String str3 = this.relativeUrl;
                this.relativeUrl = str3.replace("{" + str + "}", replace);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unable to convert path parameter \"" + str + "\" value to UTF-8:" + str2, e);
            }
        } else {
            String str4 = this.relativeUrl;
            this.relativeUrl = str4.replace("{" + str + "}", str2);
        }
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addQueryParam(String str, String str2) {
        addQueryParam(str, str2, false, true);
    }

    @Override // retrofit.RequestInterceptor.RequestFacade
    public void addEncodedQueryParam(String str, String str2) {
        addQueryParam(str, str2, false, false);
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
        if (str == null) {
            throw new IllegalArgumentException("Query param name must not be null.");
        } else if (str2 != null) {
            try {
                StringBuilder sb = this.queryParams;
                if (sb == null) {
                    sb = new StringBuilder();
                    this.queryParams = sb;
                }
                sb.append(sb.length() > 0 ? '&' : '?');
                if (z) {
                    str = URLEncoder.encode(str, "UTF-8");
                }
                if (z2) {
                    str2 = URLEncoder.encode(str2, "UTF-8");
                }
                sb.append(str);
                sb.append('=');
                sb.append(str2);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unable to convert query parameter \"" + str + "\" value to UTF-8: " + str2, e);
            }
        } else {
            throw new IllegalArgumentException("Query param \"" + str + "\" value must not be null.");
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
                throw new IllegalArgumentException("Parameter #" + (i + 1) + " query map contained null key.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setArguments(Object[] objArr) {
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
                    String value = path.value();
                    if (obj != null) {
                        addPathParam(value, obj.toString(), path.encode());
                    } else {
                        throw new IllegalArgumentException("Path parameter \"" + value + "\" value must not be null.");
                    }
                } else if (annotationType == EncodedPath.class) {
                    String value2 = ((EncodedPath) annotation).value();
                    if (obj != null) {
                        addPathParam(value2, obj.toString(), false);
                    } else {
                        throw new IllegalArgumentException("Path parameter \"" + value2 + "\" value must not be null.");
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
                        String value3 = ((retrofit.http.Header) annotation).value();
                        if (obj instanceof Iterable) {
                            for (Object obj2 : (Iterable) obj) {
                                if (obj2 != null) {
                                    addHeader(value3, obj2.toString());
                                }
                            }
                        } else if (obj.getClass().isArray()) {
                            int length2 = Array.getLength(obj);
                            for (int i2 = 0; i2 < length2; i2++) {
                                Object obj3 = Array.get(obj, i2);
                                if (obj3 != null) {
                                    addHeader(value3, obj3.toString());
                                }
                            }
                        } else {
                            addHeader(value3, obj.toString());
                        }
                    }
                } else if (annotationType == Field.class) {
                    if (obj != null) {
                        Field field = (Field) annotation;
                        String value4 = field.value();
                        boolean encodeName = field.encodeName();
                        boolean encodeValue = field.encodeValue();
                        if (obj instanceof Iterable) {
                            for (Object obj4 : (Iterable) obj) {
                                if (obj4 != null) {
                                    this.formBody.addField(value4, encodeName, obj4.toString(), encodeValue);
                                }
                            }
                        } else if (obj.getClass().isArray()) {
                            int length3 = Array.getLength(obj);
                            for (int i3 = 0; i3 < length3; i3++) {
                                Object obj5 = Array.get(obj, i3);
                                if (obj5 != null) {
                                    this.formBody.addField(value4, encodeName, obj5.toString(), encodeValue);
                                }
                            }
                        } else {
                            this.formBody.addField(value4, encodeName, obj.toString(), encodeValue);
                        }
                    }
                } else if (annotationType == FieldMap.class) {
                    if (obj != null) {
                        FieldMap fieldMap = (FieldMap) annotation;
                        boolean encodeNames = fieldMap.encodeNames();
                        boolean encodeValues = fieldMap.encodeValues();
                        for (Map.Entry entry : ((Map) obj).entrySet()) {
                            Object key = entry.getKey();
                            if (key != null) {
                                Object value5 = entry.getValue();
                                if (value5 != null) {
                                    this.formBody.addField(key.toString(), encodeNames, value5.toString(), encodeValues);
                                }
                            } else {
                                throw new IllegalArgumentException("Parameter #" + (i + 1) + " field map contained null key.");
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                } else if (annotationType == Part.class) {
                    if (obj != null) {
                        Part part = (Part) annotation;
                        String value6 = part.value();
                        String encoding = part.encoding();
                        if (obj instanceof TypedOutput) {
                            this.multipartBody.addPart(value6, encoding, (TypedOutput) obj);
                        } else if (obj instanceof String) {
                            this.multipartBody.addPart(value6, encoding, new TypedString((String) obj));
                        } else {
                            this.multipartBody.addPart(value6, encoding, this.converter.toBody(obj));
                        }
                    }
                } else if (annotationType == PartMap.class) {
                    if (obj != null) {
                        String encoding2 = ((PartMap) annotation).encoding();
                        for (Map.Entry entry2 : ((Map) obj).entrySet()) {
                            Object key2 = entry2.getKey();
                            if (key2 != null) {
                                String obj6 = key2.toString();
                                Object value7 = entry2.getValue();
                                if (value7 != null) {
                                    if (value7 instanceof TypedOutput) {
                                        this.multipartBody.addPart(obj6, encoding2, (TypedOutput) value7);
                                    } else if (value7 instanceof String) {
                                        this.multipartBody.addPart(obj6, encoding2, new TypedString((String) value7));
                                    } else {
                                        this.multipartBody.addPart(obj6, encoding2, this.converter.toBody(value7));
                                    }
                                }
                            } else {
                                throw new IllegalArgumentException("Parameter #" + (i + 1) + " part map contained null key.");
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                } else if (annotationType != Body.class) {
                    throw new IllegalArgumentException("Unknown annotation: " + annotationType.getCanonicalName());
                } else if (obj == null) {
                    throw new IllegalArgumentException("Body parameter value must not be null.");
                } else if (obj instanceof TypedOutput) {
                    this.body = (TypedOutput) obj;
                } else {
                    this.body = this.converter.toBody(obj);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Request build() throws UnsupportedEncodingException {
        MultipartTypedOutput multipartTypedOutput = this.multipartBody;
        if (multipartTypedOutput == null || multipartTypedOutput.getPartCount() != 0) {
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

    /* access modifiers changed from: private */
    public static class MimeOverridingTypedOutput implements TypedOutput {
        private final TypedOutput delegate;
        private final String mimeType;

        MimeOverridingTypedOutput(TypedOutput typedOutput, String str) {
            this.delegate = typedOutput;
            this.mimeType = str;
        }

        @Override // retrofit.mime.TypedOutput
        public String fileName() {
            return this.delegate.fileName();
        }

        @Override // retrofit.mime.TypedOutput
        public String mimeType() {
            return this.mimeType;
        }

        @Override // retrofit.mime.TypedOutput
        public long length() {
            return this.delegate.length();
        }

        @Override // retrofit.mime.TypedOutput
        public void writeTo(OutputStream outputStream) throws IOException {
            this.delegate.writeTo(outputStream);
        }
    }
}
