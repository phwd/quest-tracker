package com.facebook.common.android;

import android.app.ActivityManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ActivityManagerMethodAutoProvider extends AbstractProvider<ActivityManager> {
    @Override // javax.inject.Provider
    public ActivityManager get() {
        return AndroidModule.provideActivityManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
