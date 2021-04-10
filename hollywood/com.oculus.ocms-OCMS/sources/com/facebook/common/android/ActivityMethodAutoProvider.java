package com.facebook.common.android;

import android.app.Activity;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ActivityMethodAutoProvider extends AbstractProvider<Activity> {
    @Override // javax.inject.Provider
    public Activity get() {
        return AndroidModule.provideActivity(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
