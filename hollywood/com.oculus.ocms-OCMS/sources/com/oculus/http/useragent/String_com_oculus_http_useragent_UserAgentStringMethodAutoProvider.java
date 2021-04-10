package com.oculus.http.useragent;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_useragent_UserAgentStringMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return UserAgentModule.provideUserAgentString(UserAgentFactory._UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_ACCESS_METHOD(this));
    }
}
