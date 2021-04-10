package com.facebook.common.android;

import android.app.KeyguardManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class KeyguardManagerMethodAutoProvider extends AbstractProvider<KeyguardManager> {
    @Override // javax.inject.Provider
    public KeyguardManager get() {
        return AndroidModule.provideKeyguardManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
