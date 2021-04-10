package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import com.facebook.ultralight.UL;
import javax.net.ssl.X509TrustManager;

@TargetApi(UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID)
public class SandboxHostnameAwarePinningTrustManager extends SandboxTrustManager implements HostnameAwareX509TrustManager {
    private final X509TrustManagerExtensions mSandboxTrustManagerExtension = new X509TrustManagerExtensions((X509TrustManager) this.mTrustManagers[0]);
    private final HostnameAwareX509TrustManager mSystemHostnameAwarePinningTrustManager;

    public SandboxHostnameAwarePinningTrustManager(HostnameAwareX509TrustManager hostnameAwareX509TrustManager) {
        super(hostnameAwareX509TrustManager);
        this.mSystemHostnameAwarePinningTrustManager = hostnameAwareX509TrustManager;
    }
}
