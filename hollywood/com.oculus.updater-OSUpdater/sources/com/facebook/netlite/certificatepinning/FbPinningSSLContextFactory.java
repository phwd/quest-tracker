package com.facebook.netlite.certificatepinning;

import android.annotation.SuppressLint;
import android.os.Build;
import com.facebook.netlite.certificatepinning.internal.FbHostnameAwareExtendedTrustManager;
import com.facebook.netlite.certificatepinning.internal.FbPinningTrustManager;
import com.facebook.netlite.certificatepinning.internal.SandboxHostnameAwarePinningTrustManager;
import com.facebook.netlite.certificatepinning.internal.SandboxTrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

public class FbPinningSSLContextFactory {
    private final X509TrustManager[] mTrustManagers = new X509TrustManager[1];
    private boolean sandboxEnabled = false;

    public FbPinningSSLContextFactory(long j) {
        X509TrustManager x509TrustManager;
        X509TrustManager[] x509TrustManagerArr = this.mTrustManagers;
        if (Build.VERSION.SDK_INT >= 24) {
            x509TrustManager = new FbHostnameAwareExtendedTrustManager(j);
        } else {
            x509TrustManager = new FbPinningTrustManager(j);
        }
        x509TrustManagerArr[0] = x509TrustManager;
    }

    public void enableSandbox() {
        X509TrustManager x509TrustManager;
        if (!this.sandboxEnabled) {
            X509TrustManager[] x509TrustManagerArr = this.mTrustManagers;
            if (Build.VERSION.SDK_INT >= 17) {
                x509TrustManager = new SandboxHostnameAwarePinningTrustManager((HostnameAwareX509TrustManager) this.mTrustManagers[0]);
            } else {
                x509TrustManager = new SandboxTrustManager(this.mTrustManagers[0]);
            }
            x509TrustManagerArr[0] = x509TrustManager;
            this.sandboxEnabled = true;
        }
    }

    public X509TrustManager getTrustManager() {
        return this.mTrustManagers[0];
    }

    @SuppressLint({"Parameter Not Nullable"})
    public SSLContext create() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, this.mTrustManagers, null);
        return instance;
    }
}
