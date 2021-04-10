package com.oculus.http.useragent;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_useragent_UserAgentAppVersionMapMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return UserAgentModule.provideUserAgentAppVersionMap();
    }
}
