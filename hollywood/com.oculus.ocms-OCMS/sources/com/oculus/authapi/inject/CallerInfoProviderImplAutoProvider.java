package com.oculus.authapi.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class CallerInfoProviderImplAutoProvider extends AbstractProvider<CallerInfoProviderImpl> {
    @Override // javax.inject.Provider
    public CallerInfoProviderImpl get() {
        return new CallerInfoProviderImpl(this);
    }
}
