package com.facebook.common.android;

import android.hardware.SensorManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class SensorManagerMethodAutoProvider extends AbstractProvider<SensorManager> {
    @Override // javax.inject.Provider
    public SensorManager get() {
        return AndroidModule.provideSensorManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
