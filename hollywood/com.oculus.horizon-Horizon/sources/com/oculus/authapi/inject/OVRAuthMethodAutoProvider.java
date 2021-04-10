package com.oculus.authapi.inject;

import X.AnonymousClass0J3;
import X.C003108z;
import com.facebook.annotations.Generated;
import com.oculus.authapi.OVRAuth;

@Generated({"By: InjectorProcessor"})
public class OVRAuthMethodAutoProvider extends AnonymousClass0J3<OVRAuth> {
    public final Object get() {
        return new OVRAuth(C003108z.A00(this), CallerInfoProviderImpl.A00(this));
    }
}
