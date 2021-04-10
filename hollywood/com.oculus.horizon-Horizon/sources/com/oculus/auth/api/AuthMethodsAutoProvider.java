package com.oculus.auth.api;

import X.AnonymousClass0J3;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;

@Generated({"By: InjectorProcessor"})
public class AuthMethodsAutoProvider extends AnonymousClass0J3<AuthMethods> {
    public AuthMethods get() {
        return new AuthMethods(ApiModule.A0C(this));
    }
}
