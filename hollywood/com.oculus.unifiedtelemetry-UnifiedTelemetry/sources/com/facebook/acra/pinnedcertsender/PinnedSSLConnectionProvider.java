package com.facebook.acra.pinnedcertsender;

import X.Mu;
import X.SN;
import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PinnedSSLConnectionProvider implements HttpConnectionProvider {
    public static final String TAG = "PinnedSSLConnectionProvider";
    public final long mBuildTimeMs;
    @Nullable
    public final Proxy mProxy;
    public final int mSocketTimeout;
    @Nullable
    public SN sFbPinningSSLContextFactory;

    public void configureSocketFactory(HttpsURLConnection httpsURLConnection) {
        SN sn = this.sFbPinningSSLContextFactory;
        if (sn == null) {
            sn = new SN(this.mBuildTimeMs);
            this.sFbPinningSSLContextFactory = sn;
        }
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, sn.A00, null);
            httpsURLConnection.setSSLSocketFactory(instance.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            Mu.A03(TAG, "Pinning failed", e);
        }
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
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

    public PinnedSSLConnectionProvider(int i, long j, @Nullable Proxy proxy) {
        this.mSocketTimeout = i;
        this.mBuildTimeMs = j;
        this.mProxy = proxy;
    }
}
