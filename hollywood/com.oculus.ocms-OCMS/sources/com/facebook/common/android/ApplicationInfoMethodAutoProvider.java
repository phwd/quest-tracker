package com.facebook.common.android;

import android.content.pm.ApplicationInfo;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ApplicationInfoMethodAutoProvider extends AbstractProvider<ApplicationInfo> {
    @Override // javax.inject.Provider
    public ApplicationInfo get() {
        return AndroidModule.provideApplicationInfo(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
