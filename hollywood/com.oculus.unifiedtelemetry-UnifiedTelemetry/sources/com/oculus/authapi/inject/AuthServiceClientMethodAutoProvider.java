package com.oculus.authapi.inject;

import X.AbstractC0097Hv;
import X.C00208d;
import com.facebook.annotations.Generated;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.binder.BindingStrategy;

@Generated({"By: InjectorProcessor"})
public class AuthServiceClientMethodAutoProvider extends AbstractC0097Hv<AuthServiceClient> {
    public final Object get() {
        return new AuthServiceClient(C00208d.A00(this), BindingStrategy.DEFAULT);
    }
}
