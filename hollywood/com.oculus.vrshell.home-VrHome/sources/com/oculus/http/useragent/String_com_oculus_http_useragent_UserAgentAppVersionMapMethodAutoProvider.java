package com.oculus.http.useragent;

import com.facebook.inject.AbstractProvider;

public class String_com_oculus_http_useragent_UserAgentAppVersionMapMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return UserAgentModule.provideUserAgentAppVersionMap();
    }
}
