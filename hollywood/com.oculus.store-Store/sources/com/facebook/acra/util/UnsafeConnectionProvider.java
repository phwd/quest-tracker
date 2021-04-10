package com.facebook.acra.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class UnsafeConnectionProvider implements HttpConnectionProvider {
    @Nullable
    private Proxy mProxy;
    private final int mSocketTimeout;

    public UnsafeConnectionProvider(int socketTimeout, @Nullable Proxy proxy) {
        this.mSocketTimeout = socketTimeout;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) (this.mProxy != null ? url.openConnection(this.mProxy) : url.openConnection());
        if (conn instanceof HttpsURLConnection) {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, new TrustManager[]{new TrustEveryoneTrustManager()}, null);
                HttpsURLConnection httpsConnection = (HttpsURLConnection) conn;
                httpsConnection.setSSLSocketFactory(ctx.getSocketFactory());
                httpsConnection.setHostnameVerifier(new HostnameVerifier() {
                    /* class com.facebook.acra.util.UnsafeConnectionProvider.AnonymousClass1 */

                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
            }
        }
        return initializeConnectionParameters(conn);
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection conn) {
        conn.setConnectTimeout(this.mSocketTimeout);
        conn.setReadTimeout(this.mSocketTimeout);
        return conn;
    }
}
