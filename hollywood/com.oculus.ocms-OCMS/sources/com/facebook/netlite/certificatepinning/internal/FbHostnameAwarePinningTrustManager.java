package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.Nullable;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(17)
public class FbHostnameAwarePinningTrustManager extends FbPinningTrustManager implements HostnameAwareX509TrustManager {
    protected final X509TrustManagerExtensions trustManagerExtension;

    public FbHostnameAwarePinningTrustManager(long j) {
        this(j, null);
    }

    public FbHostnameAwarePinningTrustManager(long j, @Nullable SystemKeyStore systemKeyStore) {
        super(j, systemKeyStore);
        this.trustManagerExtension = new X509TrustManagerExtensions(this.mSystemTrustManager);
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        checkPinTrustWithCleanChain(this.trustManagerExtension.checkServerTrusted(x509CertificateArr, str, str2));
    }
}
