package com.oculus.authapi.inject;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class AuthServiceClientAuxiliaryProvider {
    public Om _UL_mInjectionContext;

    @Inject
    public AuthServiceClientAuxiliaryProvider(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
