package com.oculus.mobileconfig.init;

import android.content.pm.PackageInfo;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class PackageInfoMethodAutoProvider extends AbstractProvider<PackageInfo> {
    @Override // javax.inject.Provider
    public PackageInfo get() {
        return MobileConfigInitModule.providePackageInfo(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
