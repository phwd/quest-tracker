package com.facebook.acra.pinnedcertsender;

import X.C0139Dd;
import X.C0171Gb;
import com.facebook.acra.util.HttpConnectionProvider;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class PinnedSSLConnectionProvider implements HttpConnectionProvider {
    public static final String TAG = "PinnedSSLConnectionProvider";
    public final long mBuildTimeMs;
    public final Proxy mProxy;
    public final int mSocketTimeout;
    public C0171Gb sFbPinningSSLContextFactory;

    public void configureSocketFactory(HttpsURLConnection httpsURLConnection) {
        C0171Gb gb = this.sFbPinningSSLContextFactory;
        if (gb == null) {
            gb = new C0171Gb(this.mBuildTimeMs);
            this.sFbPinningSSLContextFactory = gb;
        }
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, gb.A01, null);
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            C0139Dd.A0M(TAG, "Pinning failed", e);
        }
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) {
        URLConnection openConnection;
        Proxy proxy = this.mProxy;
        if (proxy != null) {
            openConnection = url.openConnection(proxy);
        } else {
            openConnection = url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        if (httpURLConnection instanceof HttpsURLConnection) {
            configureSocketFactory((HttpsURLConnection) httpURLConnection);
        }
        initializeConnectionParameters(httpURLConnection);
        return httpURLConnection;
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.mSocketTimeout);
        httpURLConnection.setReadTimeout(this.mSocketTimeout);
        return httpURLConnection;
    }

    public PinnedSSLConnectionProvider(int i, long j, Proxy proxy) {
        this.mSocketTimeout = i;
        this.mBuildTimeMs = j;
        this.mProxy = proxy;
    }
}
