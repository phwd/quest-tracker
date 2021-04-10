package com.android.okhttp;

import com.android.okhttp.ConnectionSpec;
import java.net.Proxy;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public final class HttpsHandler extends HttpHandler {
    private static final List HTTP_1_1_ONLY = Collections.singletonList(Protocol.HTTP_1_1);
    private static final ConnectionSpec TLS_CONNECTION_SPEC;
    private final ConfigAwareConnectionPool configAwareConnectionPool = ConfigAwareConnectionPool.getInstance();

    /* access modifiers changed from: protected */
    @Override // com.android.okhttp.HttpHandler, java.net.URLStreamHandler
    public int getDefaultPort() {
        return 443;
    }

    static {
        ConnectionSpec.Builder builder = ConnectionSpecs.builder(true);
        builder.allEnabledCipherSuites();
        builder.allEnabledTlsVersions();
        builder.supportsTlsExtensions(true);
        TLS_CONNECTION_SPEC = builder.build();
    }

    /* access modifiers changed from: protected */
    @Override // com.android.okhttp.HttpHandler
    public OkUrlFactory newOkUrlFactory(Proxy proxy) {
        OkUrlFactory createHttpsOkUrlFactory = createHttpsOkUrlFactory(proxy);
        createHttpsOkUrlFactory.client().setConnectionPool(this.configAwareConnectionPool.get());
        return createHttpsOkUrlFactory;
    }

    public static OkUrlFactory createHttpsOkUrlFactory(Proxy proxy) {
        OkUrlFactory createHttpOkUrlFactory = HttpHandler.createHttpOkUrlFactory(proxy);
        OkUrlFactories.setUrlFilter(createHttpOkUrlFactory, null);
        OkHttpClient client = createHttpOkUrlFactory.client();
        client.setProtocols(HTTP_1_1_ONLY);
        client.setConnectionSpecs(Collections.singletonList(TLS_CONNECTION_SPEC));
        client.setCertificatePinner(CertificatePinner.DEFAULT);
        createHttpOkUrlFactory.client().setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
        client.setSslSocketFactory(HttpsURLConnection.getDefaultSSLSocketFactory());
        return createHttpOkUrlFactory;
    }
}
