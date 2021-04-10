package com.oculus.mobileconfig.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.factory.module.MobileConfigFactoryModule;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;

@Generated({"By: InjectorProcessor"})
public class MobileConfigLogoutHandlerAutoProvider extends AbstractProvider<MobileConfigLogoutHandler> {
    @Override // javax.inject.Provider
    public MobileConfigLogoutHandler get() {
        return new MobileConfigLogoutHandler(MobileConfigFactoryImplModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(this), MobileConfigFactoryModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(this), MobileConfigInit._UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_mobileconfig_init_MobileConfigInit_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
