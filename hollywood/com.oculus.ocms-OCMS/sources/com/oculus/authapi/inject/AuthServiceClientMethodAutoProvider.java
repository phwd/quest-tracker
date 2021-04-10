package com.oculus.authapi.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.authapi.AuthServiceClient;

@Generated({"By: InjectorProcessor"})
public class AuthServiceClientMethodAutoProvider extends AbstractProvider<AuthServiceClient> {
    @Override // javax.inject.Provider
    public AuthServiceClient get() {
        return OVRAuthModule.provideAuthServiceClient(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
