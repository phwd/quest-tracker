package com.facebook.acra.pinnedcertsender;

import com.facebook.acra.util.HttpConnectionProvider;
import com.facebook.debug.log.BLog;
import com.facebook.netlite.certificatepinning.FbPinningSSLContextFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;

public class PinnedSSLConnectionProvider implements HttpConnectionProvider {
    private static final String TAG = PinnedSSLConnectionProvider.class.getSimpleName();
    private final long mBuildTimeMs;
    @Nullable
    private final Proxy mProxy;
    private final int mSocketTimeout;
    @Nullable
    private FbPinningSSLContextFactory sFbPinningSSLContextFactory;

    public PinnedSSLConnectionProvider(int socketTimeout, long buildTimeMs, @Nullable Proxy proxy) {
        this.mSocketTimeout = socketTimeout;
        this.mBuildTimeMs = buildTimeMs;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) (this.mProxy != null ? url.openConnection(this.mProxy) : url.openConnection());
        if (conn instanceof HttpsURLConnection) {
            configureSocketFactory((HttpsURLConnection) conn);
        }
        return initializeConnectionParameters(conn);
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection conn) {
        conn.setConnectTimeout(this.mSocketTimeout);
        conn.setReadTimeout(this.mSocketTimeout);
        return conn;
    }

    public void configureSocketFactory(HttpsURLConnection conn) {
        if (this.sFbPinningSSLContextFactory == null) {
            this.sFbPinningSSLContextFactory = new FbPinningSSLContextFactory(this.mBuildTimeMs);
        }
        try {
            conn.setSSLSocketFactory(this.sFbPinningSSLContextFactory.create().getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            BLog.w(TAG, "Pinning failed", e);
        }
    }
}
