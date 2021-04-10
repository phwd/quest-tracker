package com.oculus.auth.util;

import com.facebook.inject.AbstractProvider;

public class AccessTokenUtilsAutoProvider extends AbstractProvider<AccessTokenUtils> {
    @Override // javax.inject.Provider
    public AccessTokenUtils get() {
        return new AccessTokenUtils(this);
    }
}
