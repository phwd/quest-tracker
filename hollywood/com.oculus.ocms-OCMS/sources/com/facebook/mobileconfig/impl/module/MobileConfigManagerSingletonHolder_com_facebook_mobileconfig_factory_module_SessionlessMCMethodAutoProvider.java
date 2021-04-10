package com.facebook.mobileconfig.impl.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;

@Generated({"By: InjectorProcessor"})
public class MobileConfigManagerSingletonHolder_com_facebook_mobileconfig_factory_module_SessionlessMCMethodAutoProvider extends AbstractProvider<MobileConfigManagerSingletonHolder> {
    @Override // javax.inject.Provider
    public MobileConfigManagerSingletonHolder get() {
        return MobileConfigFactoryImplModule.provideSessionlessMobileConfigManagerSingletonHolder();
    }
}
