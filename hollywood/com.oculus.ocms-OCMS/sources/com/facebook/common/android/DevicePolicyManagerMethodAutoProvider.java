package com.facebook.common.android;

import android.app.admin.DevicePolicyManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class DevicePolicyManagerMethodAutoProvider extends AbstractProvider<DevicePolicyManager> {
    @Override // javax.inject.Provider
    public DevicePolicyManager get() {
        return AndroidModule.provideDevicePolicyManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
