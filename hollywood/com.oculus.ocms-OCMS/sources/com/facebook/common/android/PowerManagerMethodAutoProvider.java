package com.facebook.common.android;

import android.os.PowerManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class PowerManagerMethodAutoProvider extends AbstractProvider<PowerManager> {
    @Override // javax.inject.Provider
    public PowerManager get() {
        return AndroidModule.providePowerManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
