package com.oculus.authapi.inject;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.authapi.OVRAuth;

public class OVRAuthMethodAutoProvider extends AbstractProvider<OVRAuth> {
    @Override // javax.inject.Provider
    public OVRAuth get() {
        return OVRAuthModule.provideOVRAuth(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this), CallerInfoProviderImpl.$ul_$xXXcom_oculus_authapi_inject_CallerInfoProviderImpl$xXXACCESS_METHOD(this));
    }
}
