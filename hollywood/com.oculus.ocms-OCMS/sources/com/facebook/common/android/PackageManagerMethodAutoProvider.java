package com.facebook.common.android;

import android.content.pm.PackageManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class PackageManagerMethodAutoProvider extends AbstractProvider<PackageManager> {
    @Override // javax.inject.Provider
    public PackageManager get() {
        return AndroidModule.providePackageManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
