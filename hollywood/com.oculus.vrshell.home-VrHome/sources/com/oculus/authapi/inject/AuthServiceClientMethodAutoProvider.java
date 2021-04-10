package com.oculus.authapi.inject;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.authapi.AuthServiceClient;

public class AuthServiceClientMethodAutoProvider extends AbstractProvider<AuthServiceClient> {
    @Override // javax.inject.Provider
    public AuthServiceClient get() {
        return OVRAuthModule.provideAuthServiceClient(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
