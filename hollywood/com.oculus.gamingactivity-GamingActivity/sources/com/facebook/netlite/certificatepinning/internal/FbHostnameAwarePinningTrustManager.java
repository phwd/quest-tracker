package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import androidx.core.view.MotionEventCompat;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.Nullable;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(MotionEventCompat.AXIS_LTRIGGER)
public class FbHostnameAwarePinningTrustManager extends FbPinningTrustManager implements HostnameAwareX509TrustManager {
    protected final X509TrustManagerExtensions trustManagerExtension;

    public FbHostnameAwarePinningTrustManager(long appBuildTimeInMs) {
        this(appBuildTimeInMs, null);
    }

    public FbHostnameAwarePinningTrustManager(long appBuildTimeInMs, @Nullable SystemKeyStore keyStore) {
        super(appBuildTimeInMs, keyStore);
        this.trustManagerExtension = new X509TrustManagerExtensions(this.mSystemTrustManager);
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] chain, String authType, String host) throws CertificateException {
        checkPinTrustWithCleanChain(this.trustManagerExtension.checkServerTrusted(chain, authType, host));
    }
}
