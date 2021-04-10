package com.oculus.authapi.inject;

import com.facebook.inject.AbstractProvider;

public class CallerInfoProviderImplAutoProvider extends AbstractProvider<CallerInfoProviderImpl> {
    @Override // javax.inject.Provider
    public CallerInfoProviderImpl get() {
        return new CallerInfoProviderImpl(this);
    }
}
