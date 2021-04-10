package com.oculus.mobileconfig.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInitModule;

@Generated({"By: InjectorProcessor"})
public class MobileConfigDumperPluginAutoProvider extends AbstractProvider<MobileConfigDumperPlugin> {
    @Override // javax.inject.Provider
    public MobileConfigDumperPlugin get() {
        return new MobileConfigDumperPlugin(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), MobileConfigInitModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(this), MobileConfigFactoryImplModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(this), MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_ACCESS_METHOD(this), MobileConfigConfiguration._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_ACCESS_METHOD(this));
    }
}
