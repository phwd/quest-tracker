package com.facebook.mobileconfig.impl.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;

@Generated({"By: InjectorProcessor"})
public class MobileConfigManagerSingletonHolderMethodAutoProvider extends AbstractProvider<MobileConfigManagerSingletonHolder> {
    @Override // javax.inject.Provider
    public MobileConfigManagerSingletonHolder get() {
        return MobileConfigFactoryImplModule.provideMobileConfigManagerSingletonHolder();
    }
}
