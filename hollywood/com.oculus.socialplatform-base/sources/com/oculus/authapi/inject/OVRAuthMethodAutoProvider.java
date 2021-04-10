package com.oculus.authapi.inject;

import X.AnonymousClass0VG;
import X.C00610Hs;
import com.facebook.annotations.Generated;
import com.oculus.authapi.OVRAuth;

@Generated({"By: InjectorProcessor"})
public class OVRAuthMethodAutoProvider extends AnonymousClass0VG<OVRAuth> {
    public OVRAuth get() {
        return new OVRAuth(C00610Hs.A00(this), CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_ACCESS_METHOD(this));
    }
}
