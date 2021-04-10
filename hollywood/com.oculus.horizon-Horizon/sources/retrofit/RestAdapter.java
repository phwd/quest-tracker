package retrofit;

import X.AnonymousClass006;
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
import retrofit.Utils;
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
    public static final String IDLE_THREAD_NAME = "Retrofit-Idle";
    public static final String THREAD_PREFIX = "Retrofit-";
    public final Executor callbackExecutor;
    public final Client.Provider clientProvider;
    public final Converter converter;
    public final ErrorHandler errorHandler;
    public final Executor httpExecutor;
    public final Log log;
    public volatile LogLevel logLevel;
    public final Profiler profiler;
    public final RequestInterceptor requestInterceptor;
    public RxSupport rxSupport;
    public final Endpoint server;
    public final Map<Class<?>, Map<Method, RestMethodInfo>> serviceMethodInfoCache;

    public static class Builder {
        public Executor callbackExecutor;
        public Client.Provider clientProvider;
        public Converter converter;
        public Endpoint endpoint;
        public ErrorHandler errorHandler;
        public Executor httpExecutor;
        public Log log;
        public LogLevel logLevel = LogLevel.NONE;
        public Profiler profiler;
        public RequestInterceptor requestInterceptor;

        private void ensureSaneDefaults() {
            if (this.converter == null) {
                this.converter = Platform.PLATFORM.defaultConverter();
            }
            if (this.clientProvider == null) {
                this.clientProvider = Platform.PLATFORM.defaultClient();
            }
            if (this.httpExecutor == null) {
                this.httpExecutor = Platform.PLATFORM.defaultHttpExecutor();
            }
            if (this.callbackExecutor == null) {
                this.callbackExecutor = Platform.PLATFORM.defaultCallbackExecutor();
            }
            if (this.errorHandler == null) {
                this.errorHandler = ErrorHandler.DEFAULT;
            }
            if (this.log == null) {
                this.log = Platform.PLATFORM.defaultLog();
            }
            if (this.requestInterceptor == null) {
                this.requestInterceptor = RequestInterceptor.NONE;
            }
        }

        public RestAdapter build() {
            if (this.endpoint != null) {
                ensureSaneDefaults();
                return new RestAdapter(this.endpoint, this.clientProvider, this.httpExecutor, this.callbackExecutor, this.requestInterceptor, this.converter, this.profiler, this.errorHandler, this.log, this.logLevel);
            }
            throw new IllegalArgumentException("Endpoint may not be null.");
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

        public Builder setExecutors(Executor executor, Executor executor2) {
            if (executor != null) {
                if (executor2 == null) {
                    executor2 = new Utils.SynchronousExecutor();
                }
                this.httpExecutor = executor;
                this.callbackExecutor = executor2;
                return this;
            }
            throw new NullPointerException("HTTP executor may not be null.");
        }

        public Builder setLog(Log log2) {
            if (log2 != null) {
                this.log = log2;
                return this;
            }
            throw new NullPointerException("Log may not be null.");
        }

        public Builder setLogLevel(LogLevel logLevel2) {
            if (logLevel2 != null) {
                this.logLevel = logLevel2;
                return this;
            }
            throw new NullPointerException("Log level may not be null.");
        }

        public Builder setProfiler(Profiler profiler2) {
            if (profiler2 != null) {
                this.profiler = profiler2;
                return this;
            }
            throw new NullPointerException("Profiler may not be null.");
        }

        public Builder setRequestInterceptor(RequestInterceptor requestInterceptor2) {
            if (requestInterceptor2 != null) {
                this.requestInterceptor = requestInterceptor2;
                return this;
            }
            throw new NullPointerException("Request interceptor may not be null.");
        }

        public Builder setClient(Client.Provider provider) {
            if (provider != null) {
                this.clientProvider = provider;
                return this;
            }
            throw new NullPointerException("Client provider may not be null.");
        }

        public Builder setClient(final Client client) {
            if (client != null) {
                this.clientProvider = new Client.Provider() {
                    /* class retrofit.RestAdapter.Builder.AnonymousClass1 */

                    @Override // retrofit.client.Client.Provider
                    public Client get() {
                        return client;
                    }
                };
                return this;
            }
            throw new NullPointerException("Client may not be null.");
        }

        public Builder setEndpoint(String str) {
            if (str == null || str.trim().length() == 0) {
                throw new NullPointerException("Endpoint may not be blank.");
            }
            this.endpoint = Endpoints.newFixedEndpoint(str);
            return this;
        }

        public Builder setEndpoint(Endpoint endpoint2) {
            if (endpoint2 != null) {
                this.endpoint = endpoint2;
                return this;
            }
            throw new NullPointerException("Endpoint may not be null.");
        }
    }

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
            if (this != NONE) {
                return true;
            }
            return false;
        }
    }

    public class RestHandler implements InvocationHandler {
        public final Map<Method, RestMethodInfo> methodDetailsCache;

        public RestHandler(Map<Method, RestMethodInfo> map) {
            this.methodDetailsCache = map;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0128, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x012a, code lost:
            r1 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0138, code lost:
            r13.this$0.logException(r1, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x014e, code lost:
            r13.this$0.logException(r1, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x0158, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x0159, code lost:
            throw r0;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x0138 A[Catch:{ all -> 0x015a }] */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x014e  */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x0158 A[ExcHandler: RetrofitError (r0v0 'e' retrofit.RetrofitError A[CUSTOM_DECLARE]), Splitter:B:1:0x0003] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Object invokeRequest(retrofit.RequestInterceptor r14, retrofit.RestMethodInfo r15, java.lang.Object[] r16) {
            /*
            // Method dump skipped, instructions count: 359
            */
            throw new UnsupportedOperationException("Method not decompiled: retrofit.RestAdapter.RestHandler.invokeRequest(retrofit.RequestInterceptor, retrofit.RestMethodInfo, java.lang.Object[]):java.lang.Object");
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
            } else {
                RestAdapter restAdapter = RestAdapter.this;
                Executor executor = restAdapter.httpExecutor;
                if (executor == null || restAdapter.callbackExecutor == null) {
                    throw new IllegalStateException("Asynchronous invocation requires calling setExecutors.");
                } else if (methodInfo.isObservable) {
                    RxSupport rxSupport = restAdapter.rxSupport;
                    if (rxSupport == null) {
                        if (Platform.HAS_RX_JAVA) {
                            rxSupport = new RxSupport(executor, restAdapter.errorHandler, restAdapter.requestInterceptor);
                            restAdapter.rxSupport = rxSupport;
                        } else {
                            throw new IllegalStateException("Observable method found but no RxJava on classpath.");
                        }
                    }
                    return rxSupport.createRequestObservable(new RxSupport.Invoker() {
                        /* class retrofit.RestAdapter.RestHandler.AnonymousClass1 */

                        @Override // retrofit.RxSupport.Invoker
                        public ResponseWrapper invoke(RequestInterceptor requestInterceptor) {
                            return (ResponseWrapper) RestHandler.this.invokeRequest(requestInterceptor, methodInfo, objArr);
                        }
                    });
                } else {
                    final RequestInterceptorTape requestInterceptorTape = new RequestInterceptorTape();
                    restAdapter.requestInterceptor.intercept(requestInterceptorTape);
                    RestAdapter restAdapter2 = RestAdapter.this;
                    restAdapter2.httpExecutor.execute(new CallbackRunnable((Callback) objArr[objArr.length - 1], restAdapter2.callbackExecutor, restAdapter2.errorHandler) {
                        /* class retrofit.RestAdapter.RestHandler.AnonymousClass2 */

                        @Override // retrofit.CallbackRunnable
                        public ResponseWrapper obtainResponse() {
                            return (ResponseWrapper) RestHandler.this.invokeRequest(requestInterceptorTape, methodInfo, objArr);
                        }
                    });
                    return null;
                }
            }
        }
    }

    public static RestMethodInfo getMethodInfo(Map<Method, RestMethodInfo> map, Method method) {
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

    public static Profiler.RequestInformation getRequestInfo(String str, RestMethodInfo restMethodInfo, Request request) {
        long j;
        String str2;
        TypedOutput typedOutput = request.body;
        if (typedOutput != null) {
            j = typedOutput.length();
            str2 = typedOutput.mimeType();
        } else {
            j = 0;
            str2 = null;
        }
        return new Profiler.RequestInformation(restMethodInfo.requestMethod, str, restMethodInfo.requestUrl, j, str2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Response logAndReplaceResponse(String str, Response response, long j) throws IOException {
        this.log.log(String.format("<--- HTTP %s %s (%sms)", Integer.valueOf(response.status), str, Long.valueOf(j)));
        if (this.logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            for (Header header : response.headers) {
                this.log.log(header.toString());
            }
            long j2 = 0;
            TypedInput typedInput = response.body;
            if (typedInput != null) {
                j2 = typedInput.length();
                if (this.logLevel.ordinal() >= LogLevel.FULL.ordinal()) {
                    if (!response.headers.isEmpty()) {
                        this.log.log("");
                    }
                    if (!(typedInput instanceof TypedByteArray)) {
                        response = Utils.readBodyToBytesIfNecessary(response);
                        typedInput = response.body;
                    }
                    byte[] bArr = ((TypedByteArray) typedInput).bytes;
                    j2 = (long) bArr.length;
                    this.log.log(new String(bArr, MimeUtil.parseCharset(typedInput.mimeType(), "UTF-8")));
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

    public Request logAndReplaceRequest(String str, Request request, Object[] objArr) throws IOException {
        String str2;
        this.log.log(String.format("---> %s %s %s", str, request.method, request.url));
        if (this.logLevel.ordinal() >= LogLevel.HEADERS.ordinal()) {
            for (Header header : request.headers) {
                this.log.log(header.toString());
            }
            TypedOutput typedOutput = request.body;
            if (typedOutput != null) {
                String mimeType = typedOutput.mimeType();
                if (mimeType != null) {
                    this.log.log(AnonymousClass006.A05("Content-Type: ", mimeType));
                }
                long length = typedOutput.length();
                StringBuilder sb = new StringBuilder();
                sb.append(length);
                sb.append("-byte");
                str2 = sb.toString();
                if (length != -1) {
                    this.log.log(AnonymousClass006.A04("Content-Length: ", length));
                }
                if (this.logLevel.ordinal() >= LogLevel.FULL.ordinal()) {
                    if (!request.headers.isEmpty()) {
                        this.log.log("");
                    }
                    if (!(typedOutput instanceof TypedByteArray)) {
                        request = Utils.readBodyToBytesIfNecessary(request);
                        typedOutput = request.body;
                    }
                    this.log.log(new String(((TypedByteArray) typedOutput).bytes, MimeUtil.parseCharset(typedOutput.mimeType(), "UTF-8")));
                } else if (this.logLevel.ordinal() >= LogLevel.HEADERS_AND_ARGS.ordinal()) {
                    if (!request.headers.isEmpty()) {
                        this.log.log("---> REQUEST:");
                    }
                    for (int i = 0; i < objArr.length; i++) {
                        Log log2 = this.log;
                        StringBuilder sb2 = new StringBuilder("#");
                        sb2.append(i);
                        sb2.append(": ");
                        sb2.append(objArr[i]);
                        log2.log(sb2.toString());
                    }
                }
            } else {
                str2 = "no";
            }
            this.log.log(String.format("---> END %s (%s body)", str, str2));
        }
        return request;
    }

    public void logException(Throwable th, String str) {
        Log log2 = this.log;
        Object[] objArr = new Object[1];
        if (str == null) {
            str = "";
        }
        objArr[0] = str;
        log2.log(String.format("---- ERROR %s", objArr));
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        this.log.log(stringWriter.toString());
        this.log.log("---- END ERROR");
    }

    public void setLogLevel(LogLevel logLevel2) {
        if (this.logLevel != null) {
            this.logLevel = logLevel2;
            return;
        }
        throw new NullPointerException("Log level may not be null.");
    }

    public <T> T create(Class<T> cls) {
        Utils.validateServiceClass(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new RestHandler(getMethodInfoCache(cls)));
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public RestAdapter(Endpoint endpoint, Client.Provider provider, Executor executor, Executor executor2, RequestInterceptor requestInterceptor2, Converter converter2, Profiler profiler2, ErrorHandler errorHandler2, Log log2, LogLevel logLevel2) {
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
}
