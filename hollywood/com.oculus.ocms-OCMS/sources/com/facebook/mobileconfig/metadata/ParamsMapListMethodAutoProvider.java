package com.facebook.mobileconfig.metadata;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ParamsMapListMethodAutoProvider extends AbstractProvider<ParamsMapList> {
    @Override // javax.inject.Provider
    public ParamsMapList get() {
        return MobileConfigParamsMapModule.provideParamsMapList(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
