package com.facebook.config.application;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class String_com_facebook_config_application_ApiConnectionTypeMethodAutoProvider extends AbstractProvider<String> {
    @Override // javax.inject.Provider
    public String get() {
        return FbAppTypeModule.provideApiConnectionType();
    }
}
