package com.oculus.http.common;

import X.AnonymousClass0IG;
import X.AnonymousClass0VG;
import com.facebook.annotations.Generated;
import com.oculus.http.socketconfig.SocketConfigModule;
import okhttp3.OkHttpClient;

@Generated({"By: InjectorProcessor"})
public class OkHttpClientMethodAutoProvider extends AnonymousClass0VG<OkHttpClient> {
    public OkHttpClient get() {
        return HttpModule.provideOkHttpClient(SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(this), HttpModule._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(this), HttpModule._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(this), AnonymousClass0IG.A00(this));
    }
}
