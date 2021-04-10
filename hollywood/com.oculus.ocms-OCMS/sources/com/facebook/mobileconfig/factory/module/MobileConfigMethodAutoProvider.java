package com.facebook.mobileconfig.factory.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.factory.MobileConfig;

@Generated({"By: InjectorProcessor"})
public class MobileConfigMethodAutoProvider extends AbstractProvider<MobileConfig> {
    @Override // javax.inject.Provider
    public MobileConfig get() {
        return MobileConfigFactoryModule.provideMobileConfig();
    }
}
