package com.facebook.acra.pinnedcertsender;

import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.netlite.certificatepinning.FbPinningSSLContextFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class PinnedSSLConnectionProvider implements HttpConnectionProvider {
    private static final String TAG = "PinnedSSLConnectionProvider";
    private final long mBuildTimeMs;
    @Nullable
    private final Proxy mProxy;
    private final int mSocketTimeout;
    @Nullable
    private FbPinningSSLContextFactory sFbPinningSSLContextFactory;

    public PinnedSSLConnectionProvider(int i, long j, @Nullable Proxy proxy) {
        this.mSocketTimeout = i;
        this.mBuildTimeMs = j;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        Proxy proxy = this.mProxy;
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy != null ? url.openConnection(proxy) : url.openConnection());
        if (httpURLConnection instanceof HttpsURLConnection) {
            configureSocketFactory((HttpsURLConnection) httpURLConnection);
        }
        return initializeConnectionParameters(httpURLConnection);
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.mSocketTimeout);
        httpURLConnection.setReadTimeout(this.mSocketTimeout);
        return httpURLConnection;
    }

    public void configureSocketFactory(HttpsURLConnection httpsURLConnection) {
        if (this.sFbPinningSSLContextFactory == null) {
            this.sFbPinningSSLContextFactory = new FbPinningSSLContextFactory(this.mBuildTimeMs);
        }
        try {
            httpsURLConnection.setSSLSocketFactory(this.sFbPinningSSLContextFactory.create().getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            BLog.w(TAG, "Pinning failed", e);
        }
    }
}
