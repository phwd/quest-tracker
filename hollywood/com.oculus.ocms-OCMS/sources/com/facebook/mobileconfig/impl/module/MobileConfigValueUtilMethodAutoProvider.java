package com.facebook.mobileconfig.impl.module;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;

@Generated({"By: InjectorProcessor"})
public class MobileConfigValueUtilMethodAutoProvider extends AbstractProvider<MobileConfigValueUtil> {
    @Override // javax.inject.Provider
    public MobileConfigValueUtil get() {
        return MobileConfigFactoryImplModule.provideMobileConfigValueUtil(MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_ACCESS_METHOD(this), MobileConfigFactoryModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_ACCESS_METHOD(this), MobileConfigFactoryModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(this));
    }
}
