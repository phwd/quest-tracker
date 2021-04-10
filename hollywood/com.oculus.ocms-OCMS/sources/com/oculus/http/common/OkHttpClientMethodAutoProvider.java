package com.oculus.http.common;

import com.facebook.annotations.Generated;
import com.facebook.common.manifest.ManifestModule;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.socketconfig.SocketConfigModule;
import okhttp3.OkHttpClient;

@Generated({"By: InjectorProcessor"})
public class OkHttpClientMethodAutoProvider extends AbstractProvider<OkHttpClient> {
    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return HttpModule.provideOkHttpClient(SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(this), HttpModule._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_ACCESS_METHOD(this), HttpModule._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_ACCESS_METHOD(this), ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_ACCESS_METHOD(this));
    }
}
