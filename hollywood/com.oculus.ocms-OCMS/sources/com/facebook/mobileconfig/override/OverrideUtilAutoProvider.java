package com.facebook.mobileconfig.override;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.metadata.MobileConfigParamsMapModule;

@Generated({"By: InjectorProcessor"})
public class OverrideUtilAutoProvider extends AbstractProvider<OverrideUtil> {
    @Override // javax.inject.Provider
    public OverrideUtil get() {
        return new OverrideUtil(MobileConfigParamsMapModule._UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_ACCESS_METHOD(this), MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_ACCESS_METHOD(this));
    }
}
