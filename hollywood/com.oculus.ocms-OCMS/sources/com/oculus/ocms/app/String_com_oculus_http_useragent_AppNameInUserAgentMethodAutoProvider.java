package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_oculus_http_useragent_AppNameInUserAgentMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return OCMSAppModule.provideAppNameInUserAgent();
    }
}
