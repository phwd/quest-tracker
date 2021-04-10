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

    public SandboxHostnameAwarePinningTrustManager(HostnameAwareX509TrustManager hostnameAwareX509TrustManager) {
        super(hostnameAwareX509TrustManager);
        this.mSystemHostnameAwarePinningTrustManager = hostnameAwareX509TrustManager;
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        try {
            this.mSystemHostnameAwarePinningTrustManager.checkServerTrustedUsingPeerHostName(x509CertificateArr, str, str2);
        } catch (CertificateException unused) {
            this.mSandboxTrustManagerExtension.checkServerTrusted(x509CertificateArr, str, str2);
        }
    }
}
