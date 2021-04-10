package com.oculus.oktigon;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.defaultclient.DefaultClientModule;
import okhttp3.OkHttpClient;

@Generated({"By: InjectorProcessor"})
public class OkHttpClient_com_facebook_tigon_oktigon_OkTigonClientMethodAutoProvider extends AbstractProvider<OkHttpClient> {
    @Override // javax.inject.Provider
    public OkHttpClient get() {
        return OculusOkTigonModule.getOkHttpClient(DefaultClientModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_ACCESS_METHOD(this));
    }
}
