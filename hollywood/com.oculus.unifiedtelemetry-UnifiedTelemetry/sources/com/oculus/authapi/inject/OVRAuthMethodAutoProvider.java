package com.oculus.authapi.inject;

import X.AbstractC0097Hv;
import X.C00208d;
import com.facebook.annotations.Generated;
import com.oculus.authapi.OVRAuth;

@Generated({"By: InjectorProcessor"})
public class OVRAuthMethodAutoProvider extends AbstractC0097Hv<OVRAuth> {
    public final Object get() {
        return new OVRAuth(C00208d.A00(this), CallerInfoProviderImpl.A00(this));
    }
}
