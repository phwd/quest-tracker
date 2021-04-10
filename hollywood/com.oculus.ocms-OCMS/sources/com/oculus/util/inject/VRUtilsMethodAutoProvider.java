package com.oculus.util.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.util.vr.VRUtils;

@Generated({"By: InjectorProcessor"})
public class VRUtilsMethodAutoProvider extends AbstractProvider<VRUtils> {
    @Override // javax.inject.Provider
    public VRUtils get() {
        return UtilModule.provideVRUtils(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
