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

    public UnsafeConnectionProvider(int i, @Nullable Proxy proxy) {
        this.mSocketTimeout = i;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        Proxy proxy = this.mProxy;
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy != null ? url.openConnection(proxy) : url.openConnection());
        if (httpURLConnection instanceof HttpsURLConnection) {
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init(null, new TrustManager[]{new TrustEveryoneTrustManager()}, null);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
                httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                    /* class com.facebook.acra.util.UnsafeConnectionProvider.AnonymousClass1 */

                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                });
            } catch (KeyManagementException | NoSuchAlgorithmException unused) {
            }
        }
        return initializeConnectionParameters(httpURLConnection);
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.mSocketTimeout);
        httpURLConnection.setReadTimeout(this.mSocketTimeout);
        return httpURLConnection;
    }
}
