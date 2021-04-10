package com.oculus.mobileconfig.updater;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.mobileconfig.init.MobileConfigInitModule;

@Generated({"By: InjectorProcessor"})
public class MobileConfigUpdaterInitAutoProvider extends AbstractProvider<MobileConfigUpdaterInit> {
    @Override // javax.inject.Provider
    public MobileConfigUpdaterInit get() {
        return new MobileConfigUpdaterInit(this, MobileConfigInitModule._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_ACCESS_METHOD(this));
    }
}
