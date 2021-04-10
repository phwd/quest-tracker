package com.squareup.okhttp;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.InternalCache;
import com.squareup.okhttp.internal.RouteDatabase;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.AuthenticatorAdapter;
import com.squareup.okhttp.internal.http.StreamAllocation;
import com.squareup.okhttp.internal.io.RealConnection;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class OkHttpClient implements Cloneable {
    public static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT);
    public static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
    public static SSLSocketFactory defaultSslSocketFactory;
    public Authenticator authenticator;
    public Cache cache;
    public CertificatePinner certificatePinner;
    public int connectTimeout;
    public ConnectionPool connectionPool;
    public List<ConnectionSpec> connectionSpecs;
    public CookieHandler cookieHandler;
    public Dispatcher dispatcher;
    public Dns dns;
    public boolean followRedirects;
    public boolean followSslRedirects;
    public HostnameVerifier hostnameVerifier;
    public final List<Interceptor> interceptors;
    public InternalCache internalCache;
    public final List<Interceptor> networkInterceptors;
    public List<Protocol> protocols;
    public Proxy proxy;
    public ProxySelector proxySelector;
    public int readTimeout;
    public boolean retryOnConnectionFailure;
    public final RouteDatabase routeDatabase;
    public SocketFactory socketFactory;
    public SSLSocketFactory sslSocketFactory;
    public int writeTimeout;

    private synchronized SSLSocketFactory getDefaultSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        sSLSocketFactory = defaultSslSocketFactory;
        if (sSLSocketFactory == null) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, null, null);
                sSLSocketFactory = instance.getSocketFactory();
                defaultSslSocketFactory = sSLSocketFactory;
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        return sSLSocketFactory;
    }

    static {
        Internal.instance = new Internal() {
            /* class com.squareup.okhttp.OkHttpClient.AnonymousClass1 */

            @Override // com.squareup.okhttp.internal.Internal
            public StreamAllocation callEngineGetStreamAllocation(Call call) {
                return call.engine.streamAllocation;
            }

            @Override // com.squareup.okhttp.internal.Internal
            public boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection) {
                return connectionPool.connectionBecameIdle(realConnection);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation) {
                return connectionPool.get(address, streamAllocation);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public HttpUrl getHttpUrlChecked(String str) throws MalformedURLException, UnknownHostException {
                return HttpUrl.getChecked(str);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public InternalCache internalCache(OkHttpClient okHttpClient) {
                return okHttpClient.internalCache;
            }

            @Override // com.squareup.okhttp.internal.Internal
            public RouteDatabase routeDatabase(ConnectionPool connectionPool) {
                return connectionPool.routeDatabase;
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void put(ConnectionPool connectionPool, RealConnection realConnection) {
                connectionPool.put(realConnection);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void setCache(OkHttpClient okHttpClient, InternalCache internalCache) {
                okHttpClient.setInternalCache(internalCache);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z) {
                connectionSpec.apply(sSLSocket, z);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void callEnqueue(Call call, Callback callback, boolean z) {
                call.enqueue(callback, z);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void addLenient(Headers.Builder builder, String str) {
                builder.addLenient(str);
            }

            @Override // com.squareup.okhttp.internal.Internal
            public void addLenient(Headers.Builder builder, String str, String str2) {
                builder.addLenient(str, str2);
            }
        };
    }

    public OkHttpClient cancel(Object obj) {
        this.dispatcher.cancel(obj);
        return this;
    }

    public OkHttpClient copyWithDefaults() {
        OkHttpClient okHttpClient = new OkHttpClient(this);
        if (okHttpClient.proxySelector == null) {
            okHttpClient.proxySelector = ProxySelector.getDefault();
        }
        if (okHttpClient.cookieHandler == null) {
            okHttpClient.cookieHandler = CookieHandler.getDefault();
        }
        if (okHttpClient.socketFactory == null) {
            okHttpClient.socketFactory = SocketFactory.getDefault();
        }
        if (okHttpClient.sslSocketFactory == null) {
            okHttpClient.sslSocketFactory = getDefaultSSLSocketFactory();
        }
        if (okHttpClient.hostnameVerifier == null) {
            okHttpClient.hostnameVerifier = OkHostnameVerifier.INSTANCE;
        }
        if (okHttpClient.certificatePinner == null) {
            okHttpClient.certificatePinner = CertificatePinner.DEFAULT;
        }
        if (okHttpClient.authenticator == null) {
            okHttpClient.authenticator = AuthenticatorAdapter.INSTANCE;
        }
        if (okHttpClient.connectionPool == null) {
            okHttpClient.connectionPool = ConnectionPool.systemDefault;
        }
        if (okHttpClient.protocols == null) {
            okHttpClient.protocols = DEFAULT_PROTOCOLS;
        }
        if (okHttpClient.connectionSpecs == null) {
            okHttpClient.connectionSpecs = DEFAULT_CONNECTION_SPECS;
        }
        if (okHttpClient.dns == null) {
            okHttpClient.dns = Dns.SYSTEM;
        }
        return okHttpClient;
    }

    public Call newCall(Request request) {
        return new Call(this, request);
    }

    public OkHttpClient setCache(Cache cache2) {
        this.cache = cache2;
        this.internalCache = null;
        return this;
    }

    public void setConnectTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        } else if (timeUnit != null) {
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            } else if (millis != 0 || j <= 0) {
                this.connectTimeout = (int) millis;
            } else {
                throw new IllegalArgumentException("Timeout too small.");
            }
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    public OkHttpClient setDispatcher(Dispatcher dispatcher2) {
        if (dispatcher2 != null) {
            this.dispatcher = dispatcher2;
            return this;
        }
        throw new IllegalArgumentException("dispatcher == null");
    }

    public void setInternalCache(InternalCache internalCache2) {
        this.internalCache = internalCache2;
        this.cache = null;
    }

    public void setReadTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        } else if (timeUnit != null) {
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            } else if (millis != 0 || j <= 0) {
                this.readTimeout = (int) millis;
            } else {
                throw new IllegalArgumentException("Timeout too small.");
            }
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    public void setWriteTimeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0");
        } else if (timeUnit != null) {
            long millis = timeUnit.toMillis(j);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            } else if (millis != 0 || j <= 0) {
                this.writeTimeout = (int) millis;
            } else {
                throw new IllegalArgumentException("Timeout too small.");
            }
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public Cache getCache() {
        return this.cache;
    }

    public CertificatePinner getCertificatePinner() {
        return this.certificatePinner;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public List<ConnectionSpec> getConnectionSpecs() {
        return this.connectionSpecs;
    }

    public CookieHandler getCookieHandler() {
        return this.cookieHandler;
    }

    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    public Dns getDns() {
        return this.dns;
    }

    public boolean getFollowRedirects() {
        return this.followRedirects;
    }

    public boolean getFollowSslRedirects() {
        return this.followSslRedirects;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public List<Protocol> getProtocols() {
        return this.protocols;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public boolean getRetryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public List<Interceptor> interceptors() {
        return this.interceptors;
    }

    public InternalCache internalCache() {
        return this.internalCache;
    }

    public List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }

    public RouteDatabase routeDatabase() {
        return this.routeDatabase;
    }

    public OkHttpClient setConnectionSpecs(List<ConnectionSpec> list) {
        this.connectionSpecs = Util.immutableList(list);
        return this;
    }

    public OkHttpClient setProtocols(List<Protocol> list) {
        List immutableList = Util.immutableList(list);
        if (!immutableList.contains(Protocol.HTTP_1_1)) {
            StringBuilder sb = new StringBuilder("protocols doesn't contain http/1.1: ");
            sb.append(immutableList);
            throw new IllegalArgumentException(sb.toString());
        } else if (immutableList.contains(Protocol.HTTP_1_0)) {
            StringBuilder sb2 = new StringBuilder("protocols must not contain http/1.0: ");
            sb2.append(immutableList);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!immutableList.contains(null)) {
            this.protocols = Util.immutableList(immutableList);
            return this;
        } else {
            throw new IllegalArgumentException("protocols must not contain null");
        }
    }

    public OkHttpClient setAuthenticator(Authenticator authenticator2) {
        this.authenticator = authenticator2;
        return this;
    }

    public OkHttpClient setCertificatePinner(CertificatePinner certificatePinner2) {
        this.certificatePinner = certificatePinner2;
        return this;
    }

    public OkHttpClient setConnectionPool(ConnectionPool connectionPool2) {
        this.connectionPool = connectionPool2;
        return this;
    }

    public OkHttpClient setCookieHandler(CookieHandler cookieHandler2) {
        this.cookieHandler = cookieHandler2;
        return this;
    }

    public OkHttpClient setDns(Dns dns2) {
        this.dns = dns2;
        return this;
    }

    public void setFollowRedirects(boolean z) {
        this.followRedirects = z;
    }

    public OkHttpClient setFollowSslRedirects(boolean z) {
        this.followSslRedirects = z;
        return this;
    }

    public OkHttpClient setHostnameVerifier(HostnameVerifier hostnameVerifier2) {
        this.hostnameVerifier = hostnameVerifier2;
        return this;
    }

    public OkHttpClient setProxy(Proxy proxy2) {
        this.proxy = proxy2;
        return this;
    }

    public OkHttpClient setProxySelector(ProxySelector proxySelector2) {
        this.proxySelector = proxySelector2;
        return this;
    }

    public void setRetryOnConnectionFailure(boolean z) {
        this.retryOnConnectionFailure = z;
    }

    public OkHttpClient setSocketFactory(SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
        return this;
    }

    public OkHttpClient setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
        return this;
    }

    public OkHttpClient() {
        this.interceptors = new ArrayList();
        this.networkInterceptors = new ArrayList();
        this.followSslRedirects = true;
        this.followRedirects = true;
        this.retryOnConnectionFailure = true;
        this.connectTimeout = 10000;
        this.readTimeout = 10000;
        this.writeTimeout = 10000;
        this.routeDatabase = new RouteDatabase();
        this.dispatcher = new Dispatcher();
    }

    public OkHttpClient(OkHttpClient okHttpClient) {
        InternalCache internalCache2;
        ArrayList arrayList = new ArrayList();
        this.interceptors = arrayList;
        this.networkInterceptors = new ArrayList();
        this.followSslRedirects = true;
        this.followRedirects = true;
        this.retryOnConnectionFailure = true;
        this.connectTimeout = 10000;
        this.readTimeout = 10000;
        this.writeTimeout = 10000;
        this.routeDatabase = okHttpClient.routeDatabase;
        this.dispatcher = okHttpClient.dispatcher;
        this.proxy = okHttpClient.proxy;
        this.protocols = okHttpClient.protocols;
        this.connectionSpecs = okHttpClient.connectionSpecs;
        arrayList.addAll(okHttpClient.interceptors);
        this.networkInterceptors.addAll(okHttpClient.networkInterceptors);
        this.proxySelector = okHttpClient.proxySelector;
        this.cookieHandler = okHttpClient.cookieHandler;
        Cache cache2 = okHttpClient.cache;
        this.cache = cache2;
        if (cache2 != null) {
            internalCache2 = cache2.internalCache;
        } else {
            internalCache2 = okHttpClient.internalCache;
        }
        this.internalCache = internalCache2;
        this.socketFactory = okHttpClient.socketFactory;
        this.sslSocketFactory = okHttpClient.sslSocketFactory;
        this.hostnameVerifier = okHttpClient.hostnameVerifier;
        this.certificatePinner = okHttpClient.certificatePinner;
        this.authenticator = okHttpClient.authenticator;
        this.connectionPool = okHttpClient.connectionPool;
        this.dns = okHttpClient.dns;
        this.followSslRedirects = okHttpClient.followSslRedirects;
        this.followRedirects = okHttpClient.followRedirects;
        this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure;
        this.connectTimeout = okHttpClient.connectTimeout;
        this.readTimeout = okHttpClient.readTimeout;
        this.writeTimeout = okHttpClient.writeTimeout;
    }

    @Override // java.lang.Object
    public OkHttpClient clone() {
        return new OkHttpClient(this);
    }
}
