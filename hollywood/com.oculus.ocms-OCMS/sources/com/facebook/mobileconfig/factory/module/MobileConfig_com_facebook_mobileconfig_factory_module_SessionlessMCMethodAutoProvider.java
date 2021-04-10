package com.facebook.mobileconfig.factory.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.factory.MobileConfig;

@Generated({"By: InjectorProcessor"})
public class MobileConfig_com_facebook_mobileconfig_factory_module_SessionlessMCMethodAutoProvider extends AbstractProvider<MobileConfig> {
    @Override // javax.inject.Provider
    public MobileConfig get() {
        return MobileConfigFactoryModule.provideSessionlessMobileConfig();
    }
}
