package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.auth.storage.AuthDatastore;

@Generated({"By: InjectorProcessor"})
public class AuthDatastoreMethodAutoProvider extends AbstractProvider<AuthDatastore> {
    @Override // javax.inject.Provider
    public AuthDatastore get() {
        return OCMSAppModule.provideAuthDatastore();
    }
}
