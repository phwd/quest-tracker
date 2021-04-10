package com.facebook.common.manifest;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ManifestReaderMethodAutoProvider extends AbstractProvider<ManifestReader> {
    @Override // javax.inject.Provider
    public ManifestReader get() {
        return ManifestModule.provideManifestReader(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
