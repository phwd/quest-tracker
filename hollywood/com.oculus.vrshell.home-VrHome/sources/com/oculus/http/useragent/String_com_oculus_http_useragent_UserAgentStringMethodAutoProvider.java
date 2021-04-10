package com.oculus.http.useragent;

import com.facebook.inject.AbstractProvider;

public class String_com_oculus_http_useragent_UserAgentStringMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return UserAgentModule.provideUserAgentString(UserAgentFactory.$ul_$xXXcom_oculus_http_useragent_UserAgentFactory$xXXACCESS_METHOD(this));
    }
}
