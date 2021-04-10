package com.facebook.mobileconfig.ota;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MobileConfigOTAUtilMethodAutoProvider extends AbstractProvider<MobileConfigOTAUtil> {
    @Override // javax.inject.Provider
    public MobileConfigOTAUtil get() {
        return MobileConfigOTAUtilModule.provideMobileConfigOTAUtil();
    }
}
