package com.oculus.auth.util;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AccessTokenUtilsAutoProvider extends AbstractProvider<AccessTokenUtils> {
    @Override // javax.inject.Provider
    public AccessTokenUtils get() {
        return new AccessTokenUtils(this);
    }
}
