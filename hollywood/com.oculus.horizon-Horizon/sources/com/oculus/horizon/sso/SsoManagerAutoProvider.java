package com.oculus.horizon.sso;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import com.facebook.annotations.Generated;
import retrofit.RestAdapter;

@Generated({"By: InjectorProcessor"})
public class SsoManagerAutoProvider extends AnonymousClass0J3<SsoManager> {
    public final Object get() {
        return new SsoManager(this, (RestAdapter) AnonymousClass117.A00(3, this));
    }
}
