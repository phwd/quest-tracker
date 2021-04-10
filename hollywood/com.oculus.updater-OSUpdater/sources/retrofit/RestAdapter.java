package retrofit;

import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import retrofit.Profiler;
import retrofit.RxSupport;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class RestAdapter {
    final Executor callbackExecutor;
    private final Client.Provider clientProvider;
    final Converter converter;
    final ErrorHandler errorHandler;
    final Executor httpExecutor;
    final Log log;
    volatile LogLevel logLevel;
    private final Profiler profiler;
    final RequestInterceptor requestInterceptor;
    private RxSupport rxSupport;
    final Endpoint server;
    private final Map<Class<?>, Map<Method, RestMethodInfo>> serviceMethodInfoCache;

    public interface Log {
        public static final Log NONE = new Log() {
            /* class retrofit.RestAdapter.Log.AnonymousClass1 */

            @Override // retrofit.RestAdapter.Log
            public void log(String str) {
            }
        };

        void log(String str);
    }

    public enum LogLevel {
        NONE,
        BASIC,
        HEADERS,
        HEADERS_AND_ARGS,
        FULL;

        public boolean log() {
            return this != NONE;
        }
    }

    private RestAdapter(Endpoint endpoint, Client.Provider provider, Executor executor, Executor executor2, RequestInterceptor requestInterceptor2, Converter converter2, Profiler profiler2, ErrorHandler errorHandler2, Log log2, LogLevel logLevel2) {
        this.serviceMethodInfoCache = new LinkedHashMap();
        this.server = endpoint;
        this.clientProvider = provider;
        this.httpExecutor = executor;
        this.callbackExecutor = executor2;
        this.requestInterceptor = requestInterceptor2;
        this.converter = converter2;
        this.profiler = profiler2;
        this.errorHandler = errorHandler2;
        this.log = log2;
        this.logLevel = logLevel2;
    }

    public <T> T create(Class<T> cls) {
        Utils.validateServiceClass(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new RestHandler(getMethodInfoCache(cls)));
    }

    /* access modifiers changed from: package-private */
    public Map<Method, RestMethodInfo> getMethodInfoCache(Class<?> cls) {
        Map<Method, RestMethodInfo> map;
        synchronized (this.serviceMethodInfoCache) {
            map = this.serviceMethodInfoCache.get(cls);
            if (map == null) {
                map = new LinkedHashMap<>();
                this.serviceMethodInfoCache.put(cls, map);
            }
        }
        return map;
    }

    static RestMethodInfo getMethodInfo(Map<Method, RestMethodInfo> map, Method method) {
        RestMethodInfo restMethodInfo;
        synchronized (map) {
            restMethodInfo = map.get(method);
            if (restMethodInfo == null) {
                restMethodInfo = new RestMethodInfo(method);
                map.put(method, restMethodInfo);
            }
        }
        return restMethodInfo;
    }

    /* access modifiers changed from: private */
    public class RestHandler implements InvocationHandler {
        private final Map<Method, RestMethodInfo> methodDetailsCache;

        RestHandler(Map<Method, RestMethodInfo> map) {
            this.methodDetailsCache = map;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, final Object[] objArr) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            final RestMethodInfo methodInfo = RestAdapter.getMethodInfo(this.methodDetailsCache, method);
            if (methodInfo.isSynchronous) {
                try {
                    return invokeRequest(RestAdapter.this.requestInterceptor, methodInfo, objArr);
                } catch (RetrofitError e) {
                    Throwable handleError = RestAdapter.this.errorHandler.handleError(e);
                    if (handleError == null) {
                        throw new IllegalStateException("Error handler returned null for wrapped exception.", e);
                    }
                    throw handleError;
                }
            } else if (RestAdapter.this.httpExecutor == null || RestAdapter.this.callbackExecutor == null) {
                throw new IllegalStateException("Asynchronous invocation requires calling setExecutors.");
            } else if (methodInfo.isObservable) {
                if (RestAdapter.this.rxSupport == null) {
                    if (Platform.HAS_RX_JAVA) {
                        RestAdapter restAdapter = RestAdapter.this;
                        restAdapter.rxSupport = new RxSupport(restAdapter.httpExecutor, RestAdapter.this.errorHandler, RestAdapter.this.requestInterceptor);
                    } else {
                        throw new IllegalStateException("Observable method found but no RxJava on classpath.");
                    }
                }
                return RestAdapter.this.rxSupport.createRequestObservable(new RxSupport.Invoker() {
                    /* class retrofit.RestAdapter.RestHandler.AnonymousClass1 */
                });
            } else {
                final RequestInterceptorTape requestInterceptorTape = new RequestInterceptorTape();
                RestAdapter.this.requestInterceptor.intercept(requestInterceptorTape);
                RestAdapter.this.httpExecutor.execute(new CallbackRunnable((Callback) objArr[objArr.length - 1], RestAdapter.this.callbackExecutor, RestAdapter.this.errorHandler) {
                    /* class retrofit.RestAdapter.RestHandler.AnonymousClass2 */

                    @Override // retrofit.CallbackRunnable
                    public ResponseWrapper obtainResponse() {
                        return (ResponseWrapper) RestHandler.this.invokeRequest(requestInterceptorTape, methodInfo, objArr);
                    }
                });
                return null;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x01bf, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x01c0, code lost:
            throw r0;
         */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x019f A[Catch:{ RetrofitError -> 0x01bf, IOException -> 0x0191, all -> 0x018f, RetrofitError -> 0x01bf, IOException -> 0x01a9, all -> 0x0193, all -> 0x01c1 }] */
        /* JADX WARNING: Removed duplicated region for block: B:90:0x01b5 A[Catch:{ RetrofitError -> 0x01bf, IOException -> 0x0191, all -> 0x018f, RetrofitError -> 0x01bf, IOException -> 0x01a9, all -> 0x0193, all -> 0x01c1 }] */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x01bf A[Catch:{ RetrofitError -> 0x01bf, IOException -> 0x0191, all -> 0x018f, RetrofitError -> 0x01bf, IOException -> 0x01a9, all -> 0x0193, all -> 0x01c1 }, ExcHandler: RetrofitError (r0v2 'e' retrofit.RetrofitError A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:1:0x0009] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object invokeRequest(retrofit.RequestInterceptor r17, retrofit.RestMethodInfo r18, java.lang.Object[] r19) {
            /*
            // Method dump skipped, instructions count: 462
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit.RestAdapter.RestHandler.invokeRequest(retrofit.RequestInterceptor, retrofit.RestMethodInfo, java.lang.Object[]):java.lang.Object");
        }
    }

    /* access modifiers changed from: package-private */
    public Request logAndReplaceRequest(String str, Request request, Object[] objArr) throws IOException {
        String str2;
        this.log.log(String.format("---> %s %s %s", str, request.getMethod(), request.getUrl()));
        if (this.logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            for (Header header : request.getHeaders()) {
                this.log.log(header.toString());
            }
            TypedOutput body = request.getBody();
            if (body != null) {
                String mimeType = body.mimeType();
                if (mimeType != null) {
                    this.log.log("Content-Type: " + mimeType);
                }
                long length = body.length();
                str2 = length + "-byte";
                if (length != -1) {
                    this.log.log("Content-Length: " + length);
                }
                if (this.logLevel.ordinal() >= LogLevel.FULL.ordinal()) {
                    if (!request.getHeaders().isEmpty()) {
                        this.log.log(BuildConfig.PROVIDER_SUFFIX);
                    }
                    if (!(body instanceof TypedByteArray)) {
                        request = Utils.readBodyToBytesIfNecessary(request);
                        body = request.getBody();
                    }
                    this.log.log(new String(((TypedByteArray) body).getBytes(), MimeUtil.parseCharset(body.mimeType(), "UTF-8")));
                } else if (this.logLevel.ordinal() >= LogLevel.HEADERS_AND_ARGS.ordinal()) {
                    if (!request.getHeaders().isEmpty()) {
                        this.log.log("---> REQUEST:");
                    }
                    for (int i = 0; i < objArr.length; i++) {
                        this.log.log("#" + i + ": " + objArr[i]);
                    }
                }
            } else {
                str2 = "no";
            }
            this.log.log(String.format("---> END %s (%s body)", str, str2));
        }
        return request;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Response logAndReplaceResponse(String str, Response response, long j) throws IOException {
        this.log.log(String.format("<--- HTTP %s %s (%sms)", Integer.valueOf(response.getStatus()), str, Long.valueOf(j)));
        if (this.logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            for (Header header : response.getHeaders()) {
                this.log.log(header.toString());
            }
            long j2 = 0;
            TypedInput body = response.getBody();
            if (body != null) {
                j2 = body.length();
                if (this.logLevel.ordinal() >= LogLevel.FULL.ordinal()) {
                    if (!response.getHeaders().isEmpty()) {
                        this.log.log(BuildConfig.PROVIDER_SUFFIX);
                    }
                    if (!(body instanceof TypedByteArray)) {
                        response = Utils.readBodyToBytesIfNecessary(response);
                        body = response.getBody();
                    }
                    byte[] bytes = ((TypedByteArray) body).getBytes();
                    long length = (long) bytes.length;
                    this.log.log(new String(bytes, MimeUtil.parseCharset(body.mimeType(), "UTF-8")));
                    j2 = length;
                }
            }
            this.log.log(String.format("<--- END HTTP (%s-byte body)", Long.valueOf(j2)));
        }
        return response;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logResponseBody(TypedInput typedInput, Object obj) {
        if (this.logLevel.ordinal() == LogLevel.HEADERS_AND_ARGS.ordinal()) {
            this.log.log("<--- BODY:");
            this.log.log(obj.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void logException(Throwable th, String str) {
        Log log2 = this.log;
        Object[] objArr = new Object[1];
        if (str == null) {
            str = BuildConfig.PROVIDER_SUFFIX;
        }
        objArr[0] = str;
        log2.log(String.format("---- ERROR %s", objArr));
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        this.log.log(stringWriter.toString());
        this.log.log("---- END ERROR");
    }

    /* access modifiers changed from: private */
    public static Profiler.RequestInformation getRequestInfo(String str, RestMethodInfo restMethodInfo, Request request) {
        String str2;
        long j;
        TypedOutput body = request.getBody();
        if (body != null) {
            j = body.length();
            str2 = body.mimeType();
        } else {
            j = 0;
            str2 = null;
        }
        return new Profiler.RequestInformation(restMethodInfo.requestMethod, str, restMethodInfo.requestUrl, j, str2);
    }

    public static class Builder {
        private Executor callbackExecutor;
        private Client.Provider clientProvider;
        private Converter converter;
        private Endpoint endpoint;
        private ErrorHandler errorHandler;
        private Executor httpExecutor;
        private Log log;
        private LogLevel logLevel = LogLevel.NONE;
        private Profiler profiler;
        private RequestInterceptor requestInterceptor;

        public Builder setEndpoint(String str) {
            if (str == null || str.trim().length() == 0) {
                throw new NullPointerException("Endpoint may not be blank.");
            }
            this.endpoint = Endpoints.newFixedEndpoint(str);
            return this;
        }

        public Builder setClient(final Client client) {
            if (client != null) {
                return setClient(new Client.Provider() {
                    /* class retrofit.RestAdapter.Builder.AnonymousClass1 */

                    @Override // retrofit.client.Client.Provider
                    public Client get() {
                        return client;
                    }
                });
            }
            throw new NullPointerException("Client may not be null.");
        }

        public Builder setClient(Client.Provider provider) {
            if (provider != null) {
                this.clientProvider = provider;
                return this;
            }
            throw new NullPointerException("Client provider may not be null.");
        }

        public Builder setConverter(Converter converter2) {
            if (converter2 != null) {
                this.converter = converter2;
                return this;
            }
            throw new NullPointerException("Converter may not be null.");
        }

        public Builder setErrorHandler(ErrorHandler errorHandler2) {
            if (errorHandler2 != null) {
                this.errorHandler = errorHandler2;
                return this;
            }
            throw new NullPointerException("Error handler may not be null.");
        }

        public Builder setLogLevel(LogLevel logLevel2) {
            if (logLevel2 != null) {
                this.logLevel = logLevel2;
                return this;
            }
            throw new NullPointerException("Log level may not be null.");
        }

        public RestAdapter build() {
            if (this.endpoint != null) {
                ensureSaneDefaults();
                return new RestAdapter(this.endpoint, this.clientProvider, this.httpExecutor, this.callbackExecutor, this.requestInterceptor, this.converter, this.profiler, this.errorHandler, this.log, this.logLevel);
            }
            throw new IllegalArgumentException("Endpoint may not be null.");
        }

        private void ensureSaneDefaults() {
            if (this.converter == null) {
                this.converter = Platform.get().defaultConverter();
            }
            if (this.clientProvider == null) {
                this.clientProvider = Platform.get().defaultClient();
            }
            if (this.httpExecutor == null) {
                this.httpExecutor = Platform.get().defaultHttpExecutor();
            }
            if (this.callbackExecutor == null) {
                this.callbackExecutor = Platform.get().defaultCallbackExecutor();
            }
            if (this.errorHandler == null) {
                this.errorHandler = ErrorHandler.DEFAULT;
            }
            if (this.log == null) {
                this.log = Platform.get().defaultLog();
            }
            if (this.requestInterceptor == null) {
                this.requestInterceptor = RequestInterceptor.NONE;
            }
        }
    }
}
