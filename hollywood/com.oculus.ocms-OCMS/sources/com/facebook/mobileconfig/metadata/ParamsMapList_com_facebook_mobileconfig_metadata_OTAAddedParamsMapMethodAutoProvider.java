package com.facebook.mobileconfig.metadata;

import com.facebook.annotations.Generated;
import com.facebook.gk.sessionless.GkSessionlessModule;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.mobileconfig.ota.MobileConfigOTAUtilModule;

@Generated({"By: InjectorProcessor"})
public class ParamsMapList_com_facebook_mobileconfig_metadata_OTAAddedParamsMapMethodAutoProvider extends AbstractProvider<ParamsMapList> {
    @Override // javax.inject.Provider
    public ParamsMapList get() {
        return MobileConfigParamsMapModule.provideMergedParamsList(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), MobileConfigOTAUtilModule._UL__ULSEP_com_facebook_mobileconfig_ota_MobileConfigOTAUtil_ULSEP_ACCESS_METHOD(this), GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(this));
    }
}
