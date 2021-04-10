package com.facebook.tigon.oktigon;

import com.facebook.annotations.Generated;
import com.facebook.http.config.HttpConfigModule;
import com.facebook.inject.AbstractProvider;
import okhttp3.OkHttpClient;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_facebook_tigon_oktigon_OkTigonClientMethodAutoProvider extends AbstractProvider<OkHttpClient> {
    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return OkTigonModule.getOkHttpClient(HttpConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_ACCESS_METHOD(this));
    }
}
