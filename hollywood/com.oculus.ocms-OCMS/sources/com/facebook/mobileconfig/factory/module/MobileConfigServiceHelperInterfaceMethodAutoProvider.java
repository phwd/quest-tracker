package com.facebook.mobileconfig.factory.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.interfaces.MobileConfigServiceHelperInterface;

@Generated({"By: InjectorProcessor"})
public class MobileConfigServiceHelperInterfaceMethodAutoProvider extends AbstractProvider<MobileConfigServiceHelperInterface> {
    @Override // javax.inject.Provider
    public MobileConfigServiceHelperInterface get() {
        return MobileConfigFactoryModule.provideMobileConfigServiceHelperInterface();
    }
}
