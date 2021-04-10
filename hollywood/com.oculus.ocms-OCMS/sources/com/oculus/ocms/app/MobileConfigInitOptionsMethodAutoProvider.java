package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.mobileconfig.init.MobileConfigInitOptions;

@Generated({"By: InjectorProcessor"})
public class MobileConfigInitOptionsMethodAutoProvider extends AbstractProvider<MobileConfigInitOptions> {
    @Override // javax.inject.Provider
    public MobileConfigInitOptions get() {
        return OCMSAppModule.provideMobileConfigInitOptions();
    }
}
