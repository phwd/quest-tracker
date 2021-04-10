package com.facebook.common.manifest;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AppBuildInfoMethodAutoProvider extends AbstractProvider<AppBuildInfo> {
    @Override // javax.inject.Provider
    public AppBuildInfo get() {
        return ManifestModule.provideAppBuildInfo(ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_ACCESS_METHOD(this));
    }
}
