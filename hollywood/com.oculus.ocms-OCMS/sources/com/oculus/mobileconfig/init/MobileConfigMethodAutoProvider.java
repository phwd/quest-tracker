package com.oculus.mobileconfig.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.oculus.auth.credentials.CredentialsModule;

@Generated({"By: InjectorProcessor"})
public class MobileConfigMethodAutoProvider extends AbstractProvider<MobileConfig> {
    @Override // javax.inject.Provider
    public MobileConfig get() {
        return MobileConfigInitModule.provideMobileConfig(MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_ACCESS_METHOD(this), BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), CredentialsModule._UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_credentials_Credentials_ULGT__ULSEP_ACCESS_METHOD(this));
    }
}
