package com.oculus.authapi.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AuthServiceClientAuxiliaryProviderAutoProvider extends AbstractProvider<AuthServiceClientAuxiliaryProvider> {
    @Override // javax.inject.Provider
    public AuthServiceClientAuxiliaryProvider get() {
        return new AuthServiceClientAuxiliaryProvider(this);
    }
}
