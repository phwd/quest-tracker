package com.oculus.authapi.inject;

import X.AbstractC0029Ba;
import X.IX;
import android.content.Context;
import com.facebook.annotations.Generated;
import com.oculus.authapi.AuthServiceClient;
import com.oculus.binder.BindingStrategy;

@Generated({"By: InjectorProcessor"})
public class AuthServiceClientMethodAutoProvider extends AbstractC0029Ba<AuthServiceClient> {
    public final Object get() {
        return new AuthServiceClient((Context) IX.A00(1, this), BindingStrategy.DEFAULT);
    }
}
