package com.facebook.config.application;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class PlatformAppConfigMethodAutoProvider extends AbstractProvider<PlatformAppConfig> {
    @Override // javax.inject.Provider
    public PlatformAppConfig get() {
        return FbAppTypeModule.providePlatformAppConfig(FbAppTypeModule._UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_ACCESS_METHOD(this));
    }
}
