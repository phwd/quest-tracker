package com.oculus.library.security;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class TrustedApplicationsAutoProvider extends AbstractProvider<TrustedApplications> {
    @Override // javax.inject.Provider
    public TrustedApplications get() {
        return new TrustedApplications(this, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
