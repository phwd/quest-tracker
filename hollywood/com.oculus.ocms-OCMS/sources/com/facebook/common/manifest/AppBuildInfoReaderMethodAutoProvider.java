package com.facebook.common.manifest;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AppBuildInfoReaderMethodAutoProvider extends AbstractProvider<AppBuildInfoReader> {
    @Override // javax.inject.Provider
    public AppBuildInfoReader get() {
        return ManifestModule.provideAppBuildInfoReader(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), ManifestModule._UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_ACCESS_METHOD(this));
    }
}
