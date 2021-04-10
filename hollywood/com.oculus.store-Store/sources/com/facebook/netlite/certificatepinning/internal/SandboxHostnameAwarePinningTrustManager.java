package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

@TargetApi(17)
public class SandboxHostnameAwarePinningTrustManager extends SandboxTrustManager implements HostnameAwareX509TrustManager {
    private final X509TrustManagerExtensions mSandboxTrustManagerExtension = new X509TrustManagerExtensions((X509TrustManager) this.mTrustManagers[0]);
    private final HostnameAwareX509TrustManager mSystemHostnameAwarePinningTrustManager;

    public SandboxHostnameAwarePinningTrustManager(HostnameAwareX509TrustManager fbHostnameAwarePinningTrustManager) {
        super(fbHostnameAwarePinningTrustManager);
        this.mSystemHostnameAwarePinningTrustManager = fbHostnameAwarePinningTrustManager;
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] chain, String authType, String host) throws CertificateException {
        try {
            this.mSystemHostnameAwarePinningTrustManager.checkServerTrustedUsingPeerHostName(chain, authType, host);
        } catch (CertificateException e) {
            this.mSandboxTrustManagerExtension.checkServerTrusted(chain, authType, host);
        }
    }
}
