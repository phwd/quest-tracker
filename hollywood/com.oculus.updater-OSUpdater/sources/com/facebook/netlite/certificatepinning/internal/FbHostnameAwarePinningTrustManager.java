package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import com.facebook.ultralight.UL;
import javax.annotation.Nullable;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID)
public class FbHostnameAwarePinningTrustManager extends FbPinningTrustManager implements HostnameAwareX509TrustManager {
    protected final X509TrustManagerExtensions trustManagerExtension = new X509TrustManagerExtensions(this.mSystemTrustManager);

    public FbHostnameAwarePinningTrustManager(long j, @Nullable SystemKeyStore systemKeyStore) {
        super(j, systemKeyStore);
    }
}
