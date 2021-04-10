package com.oculus.appmanager.installer.common;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class EcdsaSignatureVerifierAutoProvider extends AbstractProvider<EcdsaSignatureVerifier> {
    @Override // javax.inject.Provider
    public EcdsaSignatureVerifier get() {
        return new EcdsaSignatureVerifier();
    }
}
