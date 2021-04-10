package com.oculus.oktigon;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.http.useragent.UserAgentModule;

@Generated({"By: InjectorProcessor"})
public class String_com_facebook_http_annotations_UserAgentStringMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return OculusOkTigonModule.provideUserAgentString(UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_ACCESS_METHOD(this));
    }
}
