package com.oculus.authapi.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.authapi.OVRAuth;

@Generated({"By: InjectorProcessor"})
public class OVRAuthMethodAutoProvider extends AbstractProvider<OVRAuth> {
    @Override // javax.inject.Provider
    public OVRAuth get() {
        return OVRAuthModule.provideOVRAuth(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_ACCESS_METHOD(this));
    }
}
