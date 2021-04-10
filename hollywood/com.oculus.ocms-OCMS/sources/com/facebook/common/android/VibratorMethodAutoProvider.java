package com.facebook.common.android;

import android.os.Vibrator;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class VibratorMethodAutoProvider extends AbstractProvider<Vibrator> {
    @Override // javax.inject.Provider
    public Vibrator get() {
        return AndroidModule.provideVibrator(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
