package com.facebook.netlite.certificatepinning;

import android.annotation.SuppressLint;
import android.os.Build;
import com.facebook.netlite.certificatepinning.internal.FbHostnameAwareExtendedTrustManager;
import com.facebook.netlite.certificatepinning.internal.FbHostnameAwarePinningTrustManager;
import com.facebook.netlite.certificatepinning.internal.FbPinningTrustManager;
import com.facebook.netlite.certificatepinning.internal.SandboxHostnameAwarePinningTrustManager;
import com.facebook.netlite.certificatepinning.internal.SandboxTrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

public class FbPinningSSLContextFactory {
    private final X509TrustManager[] mTrustManagers;
    private boolean sandboxEnabled;

    public FbPinningSSLContextFactory(long appBuildTimeInMs) {
        X509TrustManager fbPinningTrustManager;
        this.mTrustManagers = new X509TrustManager[1];
        this.sandboxEnabled = false;
        X509TrustManager[] x509TrustManagerArr = this.mTrustManagers;
        if (Build.VERSION.SDK_INT >= 24) {
            fbPinningTrustManager = new FbHostnameAwareExtendedTrustManager(appBuildTimeInMs);
        } else {
            fbPinningTrustManager = new FbPinningTrustManager(appBuildTimeInMs);
        }
        x509TrustManagerArr[0] = fbPinningTrustManager;
    }

    public FbPinningSSLContextFactory(long appBuildTimeInMs, boolean useSocketBasedHostnameAwareAPI) {
        X509TrustManager fbPinningTrustManager;
        this.mTrustManagers = new X509TrustManager[1];
        this.sandboxEnabled = false;
        X509TrustManager[] x509TrustManagerArr = this.mTrustManagers;
        if (useSocketBasedHostnameAwareAPI && Build.VERSION.SDK_INT >= 24) {
            fbPinningTrustManager = new FbHostnameAwareExtendedTrustManager(appBuildTimeInMs);
        } else if (Build.VERSION.SDK_INT >= 17) {
            fbPinningTrustManager = new FbHostnameAwarePinningTrustManager(appBuildTimeInMs);
        } else {
            fbPinningTrustManager = new FbPinningTrustManager(appBuildTimeInMs);
        }
        x509TrustManagerArr[0] = fbPinningTrustManager;
    }

    public void enableSandbox() {
        SandboxTrustManager sandboxTrustManager;
        if (!this.sandboxEnabled) {
            X509TrustManager[] x509TrustManagerArr = this.mTrustManagers;
            if (Build.VERSION.SDK_INT >= 17) {
                sandboxTrustManager = new SandboxHostnameAwarePinningTrustManager((HostnameAwareX509TrustManager) this.mTrustManagers[0]);
            } else {
                sandboxTrustManager = new SandboxTrustManager(this.mTrustManagers[0]);
            }
            x509TrustManagerArr[0] = sandboxTrustManager;
            this.sandboxEnabled = true;
        }
    }

    public X509TrustManager getTrustManager() {
        return this.mTrustManagers[0];
    }

    @SuppressLint({"Parameter Not Nullable"})
    public SSLContext create() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, this.mTrustManagers, null);
        return sslContext;
    }
}
