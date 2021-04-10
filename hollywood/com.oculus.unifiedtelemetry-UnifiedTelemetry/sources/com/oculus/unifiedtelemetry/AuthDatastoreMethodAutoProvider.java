package com.oculus.unifiedtelemetry;

import X.AbstractC0097Hv;
import com.facebook.annotations.Generated;
import com.oculus.auth.storage.AuthDatastore;
import com.oculus.unifiedtelemetry.credentialsmanager.NoopAuthDatastore;

@Generated({"By: InjectorProcessor"})
public class AuthDatastoreMethodAutoProvider extends AbstractC0097Hv<AuthDatastore> {
    public final Object get() {
        return new NoopAuthDatastore();
    }
}
