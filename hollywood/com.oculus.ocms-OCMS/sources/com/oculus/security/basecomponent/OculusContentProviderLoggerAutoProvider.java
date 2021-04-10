package com.oculus.security.basecomponent;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusContentProviderLoggerAutoProvider extends AbstractProvider<OculusContentProviderLogger> {
    @Override // javax.inject.Provider
    public OculusContentProviderLogger get() {
        return new OculusContentProviderLogger(this);
    }
}
