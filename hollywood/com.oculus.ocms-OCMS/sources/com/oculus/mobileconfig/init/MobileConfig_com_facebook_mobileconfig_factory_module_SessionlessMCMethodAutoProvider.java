package com.oculus.mobileconfig.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;

@Generated({"By: InjectorProcessor"})
public class MobileConfig_com_facebook_mobileconfig_factory_module_SessionlessMCMethodAutoProvider extends AbstractProvider<MobileConfig> {
    @Override // javax.inject.Provider
    public MobileConfig get() {
        return MobileConfigInitModule.provideSessionlessMobileConfig(MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_ACCESS_METHOD(this), BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
