package com.oculus.authapi.inject;

import X.AbstractC0029Ba;
import X.IX;
import android.content.Context;
import com.facebook.annotations.Generated;
import com.oculus.authapi.OVRAuth;

@Generated({"By: InjectorProcessor"})
public class OVRAuthMethodAutoProvider extends AbstractC0029Ba<OVRAuth> {
    public final Object get() {
        return new OVRAuth((Context) IX.A00(1, this), (CallerInfoProviderImpl) IX.A00(28, this));
    }
}
