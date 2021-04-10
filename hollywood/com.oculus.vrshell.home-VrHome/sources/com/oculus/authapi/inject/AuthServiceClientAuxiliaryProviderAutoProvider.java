package com.oculus.authapi.inject;

import com.facebook.inject.AbstractProvider;

public class AuthServiceClientAuxiliaryProviderAutoProvider extends AbstractProvider<AuthServiceClientAuxiliaryProvider> {
    @Override // javax.inject.Provider
    public AuthServiceClientAuxiliaryProvider get() {
        return new AuthServiceClientAuxiliaryProvider(this);
    }
}
