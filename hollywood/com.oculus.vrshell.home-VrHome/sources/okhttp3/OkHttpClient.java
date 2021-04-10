package okhttp3;

import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;

public class OkHttpClient implements Cloneable, Call.Factory, WebSocket.Factory {
    static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT);
    static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2, Protocol.HTTP_1_1);
    final Authenticator authenticator;
    final Cache cache;
    final CertificateChainCleaner certificateChainCleaner;
    final CertificatePinner certificatePinner;
    final int connectTimeout;
    final ConnectionPool connectionPool;
    final List<ConnectionSpec> connectionSpecs;
    final CookieJar cookieJar;
    final Dispatcher dispatcher;
    final Dns dns;
    final boolean followRedirects;
    final boolean followSslRedirects;
    final HostnameVerifier hostnameVerifier;
    final List<Interceptor> interceptors;
    final InternalCache internalCache;
    final List<Interceptor> networkInterceptors;
    final int pingInterval;
    final List<Protocol> protocols;
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final int readTimeout;
    final boolean retryOnConnectionFailure;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final int writeTimeout;

    static {
        Internal.instance = new Internal() {
            /* class okhttp3.OkHttpClient.AnonymousClass1 */

            @Override // okhttp3.internal.Internal
            public void addLenient(Headers.Builder builder, String line) {
                builder.addLenient(line);
            }

            @Override // okhttp3.internal.Internal
            public void addLenient(Headers.Builder builder, String name, String value) {
                builder.addLenient(name, value);
            }

            @Override // okhttp3.internal.Internal
            public void setCache(Builder builder, InternalCache internalCache) {
                builder.setInternalCache(internalCache);
            }

            @Override // okhttp3.internal.Internal
            public boolean connectionBecameIdle(ConnectionPool pool, RealConnection connection) {
                return pool.connectionBecameIdle(connection);
            }

            @Override // okhttp3.internal.Internal
            public RealConnection get(ConnectionPool pool, Address address, StreamAllocation streamAllocation) {
                return pool.get(address, streamAllocation);
            }

            @Override // okhttp3.internal.Internal
            public Socket deduplicate(ConnectionPool pool, Address address, StreamAllocation streamAllocation) {
                return pool.deduplicate(address, streamAllocation);
            }

            @Override // okhttp3.internal.Internal
            public void put(ConnectionPool pool, RealConnection connection) {
                pool.put(connection);
            }

            @Override // okhttp3.internal.Internal
            public RouteDatabase routeDatabase(ConnectionPool connectionPool) {
                return connectionPool.routeDatabase;
            }

            @Override // okhttp3.internal.Internal
            public int code(Response.Builder responseBuilder) {
                return responseBuilder.code;
            }

            @Override // okhttp3.internal.Internal
            public void apply(ConnectionSpec tlsConfiguration, SSLSocket sslSocket, boolean isFallback) {
                tlsConfiguration.apply(sslSocket, isFallback);
            }

            @Override // okhttp3.internal.Internal
            public HttpUrl getHttpUrlChecked(String url) throws MalformedURLException, UnknownHostException {
                return HttpUrl.getChecked(url);
            }

            @Override // okhttp3.internal.Internal
            public StreamAllocation streamAllocation(Call call) {
                return ((RealCall) call).streamAllocation();
            }

            @Override // okhttp3.internal.Internal
            public Call newWebSocketCall(OkHttpClient client, Request originalRequest) {
                return new RealCall(client, originalRequest, true);
            }
        };
    }

    public OkHttpClient() {
        this(new Builder());
    }

    OkHttpClient(Builder builder) {
        this.dispatcher = builder.dispatcher;
        this.proxy = builder.proxy;
        this.protocols = builder.protocols;
        this.connectionSpecs = builder.connectionSpecs;
        this.interceptors = Util.immutableList(builder.interceptors);
        this.networkInterceptors = Util.immutableList(builder.networkInterceptors);
        this.proxySelector = builder.proxySelector;
        this.cookieJar = builder.cookieJar;
        this.cache = builder.cache;
        this.internalCache = builder.internalCache;
        this.socketFactory = builder.socketFactory;
        boolean isTLS = false;
        for (ConnectionSpec spec : this.connectionSpecs) {
            isTLS = isTLS || spec.isTls();
        }
        if (builder.sslSocketFactory != null || !isTLS) {
            this.sslSocketFactory = builder.sslSocketFactory;
            this.certificateChainCleaner = builder.certificateChainCleaner;
        } else {
            X509TrustManager trustManager = systemDefaultTrustManager();
            this.sslSocketFactory = systemDefaultSslSocketFactory(trustManager);
            this.certificateChainCleaner = CertificateChainCleaner.get(trustManager);
        }
        this.hostnameVerifier = builder.hostnameVerifier;
        this.certificatePinner = builder.certificatePinner.withCertificateChainCleaner(this.certificateChainCleaner);
        this.proxyAuthenticator = builder.proxyAuthenticator;
        this.authenticator = builder.authenticator;
        this.connectionPool = builder.connectionPool;
        this.dns = builder.dns;
        this.followSslRedirects = builder.followSslRedirects;
        this.followRedirects = builder.followRedirects;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.pingInterval = builder.pingInterval;
    }

    private X509TrustManager systemDefaultTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    private SSLSocketFactory systemDefaultSslSocketFactory(X509TrustManager trustManager) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            return sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public int pingIntervalMillis() {
        return this.pingInterval;
    }

    public Proxy proxy() {
        return this.proxy;
    }

    public ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public CookieJar cookieJar() {
        return this.cookieJar;
    }

    public Cache cache() {
        return this.cache;
    }

    /* access modifiers changed from: package-private */
    public InternalCache internalCache() {
        return this.cache != null ? this.cache.internalCache : this.internalCache;
    }

    public Dns dns() {
        return this.dns;
    }

    public SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public Authenticator authenticator() {
        return this.authenticator;
    }

    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    public boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public boolean followRedirects() {
        return this.followRedirects;
    }

    public boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public Dispatcher dispatcher() {
        return this.dispatcher;
    }

    public List<Protocol> protocols() {
        return this.protocols;
    }

    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    @Override // okhttp3.Call.Factory
    public Call newCall(Request request) {
        return new RealCall(this, request, false);
    }

    @Override // okhttp3.WebSocket.Factory
    public WebSocket newWebSocket(Request request, WebSocketListener listener) {
        RealWebSocket webSocket = new RealWebSocket(request, listener, new SecureRandom());
        webSocket.connect(this);
        return webSocket;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        Authenticator authenticator;
        Cache cache;
        CertificateChainCleaner certificateChainCleaner;
        CertificatePinner certificatePinner;
        int connectTimeout;
        ConnectionPool connectionPool;
        List<ConnectionSpec> connectionSpecs;
        CookieJar cookieJar;
        Dispatcher dispatcher;
        Dns dns;
        boolean followRedirects;
        boolean followSslRedirects;
        HostnameVerifier hostnameVerifier;
        final List<Interceptor> interceptors;
        InternalCache internalCache;
        final List<Interceptor> networkInterceptors;
        int pingInterval;
        List<Protocol> protocols;
        Proxy proxy;
        Authenticator proxyAuthenticator;
        ProxySelector proxySelector;
        int readTimeout;
        boolean retryOnConnectionFailure;
        SocketFactory socketFactory;
        SSLSocketFactory sslSocketFactory;
        int writeTimeout;

        public Builder() {
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.dispatcher = new Dispatcher();
            this.protocols = OkHttpClient.DEFAULT_PROTOCOLS;
            this.connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
            this.proxySelector = ProxySelector.getDefault();
            this.cookieJar = CookieJar.NO_COOKIES;
            this.socketFactory = SocketFactory.getDefault();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.proxyAuthenticator = Authenticator.NONE;
            this.authenticator = Authenticator.NONE;
            this.connectionPool = new ConnectionPool();
            this.dns = Dns.SYSTEM;
            this.followSslRedirects = true;
            this.followRedirects = true;
            this.retryOnConnectionFailure = true;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.pingInterval = 0;
        }

        Builder(OkHttpClient okHttpClient) {
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.dispatcher = okHttpClient.dispatcher;
            this.proxy = okHttpClient.proxy;
            this.protocols = okHttpClient.protocols;
            this.connectionSpecs = okHttpClient.connectionSpecs;
            this.interceptors.addAll(okHttpClient.interceptors);
            this.networkInterceptors.addAll(okHttpClient.networkInterceptors);
            this.proxySelector = okHttpClient.proxySelector;
            this.cookieJar = okHttpClient.cookieJar;
            this.internalCache = okHttpClient.internalCache;
            this.cache = okHttpClient.cache;
            this.socketFactory = okHttpClient.socketFactory;
            this.sslSocketFactory = okHttpClient.sslSocketFactory;
            this.certificateChainCleaner = okHttpClient.certificateChainCleaner;
            this.hostnameVerifier = okHttpClient.hostnameVerifier;
            this.certificatePinner = okHttpClient.certificatePinner;
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator;
            this.authenticator = okHttpClient.authenticator;
            this.connectionPool = okHttpClient.connectionPool;
            this.dns = okHttpClient.dns;
            this.followSslRedirects = okHttpClient.followSslRedirects;
            this.followRedirects = okHttpClient.followRedirects;
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure;
            this.connectTimeout = okHttpClient.connectTimeout;
            this.readTimeout = okHttpClient.readTimeout;
            this.writeTimeout = okHttpClient.writeTimeout;
            this.pingInterval = okHttpClient.pingInterval;
        }

        public Builder connectTimeout(long timeout, TimeUnit unit) {
            this.connectTimeout = checkDuration("timeout", timeout, unit);
            return this;
        }

        public Builder readTimeout(long timeout, TimeUnit unit) {
            this.readTimeout = checkDuration("timeout", timeout, unit);
            return this;
        }

        public Builder writeTimeout(long timeout, TimeUnit unit) {
            this.writeTimeout = checkDuration("timeout", timeout, unit);
            return this;
        }

        public Builder pingInterval(long interval, TimeUnit unit) {
            this.pingInterval = checkDuration("interval", interval, unit);
            return this;
        }

        private static int checkDuration(String name, long duration, TimeUnit unit) {
            if (duration < 0) {
                throw new IllegalArgumentException(name + " < 0");
            } else if (unit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long millis = unit.toMillis(duration);
                if (millis > 2147483647L) {
                    throw new IllegalArgumentException(name + " too large.");
                } else if (millis != 0 || duration <= 0) {
                    return (int) millis;
                } else {
                    throw new IllegalArgumentException(name + " too small.");
                }
            }
        }

        public Builder proxy(Proxy proxy2) {
            this.proxy = proxy2;
            return this;
        }

        public Builder proxySelector(ProxySelector proxySelector2) {
            this.proxySelector = proxySelector2;
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar2) {
            if (cookieJar2 == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.cookieJar = cookieJar2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public void setInternalCache(InternalCache internalCache2) {
            this.internalCache = internalCache2;
            this.cache = null;
        }

        public Builder cache(Cache cache2) {
            this.cache = cache2;
            this.internalCache = null;
            return this;
        }

        public Builder dns(Dns dns2) {
            if (dns2 == null) {
                throw new NullPointerException("dns == null");
            }
            this.dns = dns2;
            return this;
        }

        public Builder socketFactory(SocketFactory socketFactory2) {
            if (socketFactory2 == null) {
                throw new NullPointerException("socketFactory == null");
            }
            this.socketFactory = socketFactory2;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory2) {
            if (sslSocketFactory2 == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            X509TrustManager trustManager = Platform.get().trustManager(sslSocketFactory2);
            if (trustManager == null) {
                throw new IllegalStateException("Unable to extract the trust manager on " + Platform.get() + ", sslSocketFactory is " + sslSocketFactory2.getClass());
            }
            this.sslSocketFactory = sslSocketFactory2;
            this.certificateChainCleaner = CertificateChainCleaner.get(trustManager);
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory2, X509TrustManager trustManager) {
            if (sslSocketFactory2 == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (trustManager == null) {
                throw new NullPointerException("trustManager == null");
            } else {
                this.sslSocketFactory = sslSocketFactory2;
                this.certificateChainCleaner = CertificateChainCleaner.get(trustManager);
                return this;
            }
        }

        public Builder hostnameVerifier(HostnameVerifier hostnameVerifier2) {
            if (hostnameVerifier2 == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.hostnameVerifier = hostnameVerifier2;
            return this;
        }

        public Builder certificatePinner(CertificatePinner certificatePinner2) {
            if (certificatePinner2 == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.certificatePinner = certificatePinner2;
            return this;
        }

        public Builder authenticator(Authenticator authenticator2) {
            if (authenticator2 == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.authenticator = authenticator2;
            return this;
        }

        public Builder proxyAuthenticator(Authenticator proxyAuthenticator2) {
            if (proxyAuthenticator2 == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.proxyAuthenticator = proxyAuthenticator2;
            return this;
        }

        public Builder connectionPool(ConnectionPool connectionPool2) {
            if (connectionPool2 == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.connectionPool = connectionPool2;
            return this;
        }

        public Builder followSslRedirects(boolean followProtocolRedirects) {
            this.followSslRedirects = followProtocolRedirects;
            return this;
        }

        public Builder followRedirects(boolean followRedirects2) {
            this.followRedirects = followRedirects2;
            return this;
        }

        public Builder retryOnConnectionFailure(boolean retryOnConnectionFailure2) {
            this.retryOnConnectionFailure = retryOnConnectionFailure2;
            return this;
        }

        public Builder dispatcher(Dispatcher dispatcher2) {
            if (dispatcher2 == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.dispatcher = dispatcher2;
            return this;
        }

        public Builder protocols(List<Protocol> protocols2) {
            List<Protocol> protocols3 = new ArrayList<>(protocols2);
            if (!protocols3.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + protocols3);
            } else if (protocols3.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + protocols3);
            } else if (protocols3.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            } else {
                if (protocols3.contains(Protocol.SPDY_3)) {
                    protocols3.remove(Protocol.SPDY_3);
                }
                this.protocols = Collections.unmodifiableList(protocols3);
                return this;
            }
        }

        public Builder connectionSpecs(List<ConnectionSpec> connectionSpecs2) {
            this.connectionSpecs = Util.immutableList(connectionSpecs2);
            return this;
        }

        public List<Interceptor> interceptors() {
            return this.interceptors;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            this.networkInterceptors.add(interceptor);
            return this;
        }

        public OkHttpClient build() {
            return new OkHttpClient(this);
        }
    }
}
