package com.facebook.mobileconfig.factory.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.interfaces.MobileConfigInitInterface;

@Generated({"By: InjectorProcessor"})
public class MobileConfigInitInterfaceMethodAutoProvider extends AbstractProvider<MobileConfigInitInterface> {
    @Override // javax.inject.Provider
    public MobileConfigInitInterface get() {
        return MobileConfigFactoryModule.provideMobileConfigInitInterface();
    }
}
