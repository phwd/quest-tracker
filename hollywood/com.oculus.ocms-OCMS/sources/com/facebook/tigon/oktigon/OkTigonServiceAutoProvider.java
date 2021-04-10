package com.facebook.tigon.oktigon;

import com.facebook.annotations.Generated;
import com.facebook.http.annotations.HttpAnnotationsModule;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OkTigonServiceAutoProvider extends AbstractProvider<OkTigonService> {
    @Override // javax.inject.Provider
    public OkTigonService get() {
        return new OkTigonService(OkTigonModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_ACCESS_METHOD(this), HttpAnnotationsModule._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_ACCESS_METHOD(this));
    }
}
