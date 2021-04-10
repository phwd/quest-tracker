package com.oculus.mobileconfig.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MobileConfigConfigurationAutoProvider extends AbstractProvider<MobileConfigConfiguration> {
    @Override // javax.inject.Provider
    public MobileConfigConfiguration get() {
        return new MobileConfigConfiguration(this, MobileConfigInitModule._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_ACCESS_METHOD(this));
    }
}
