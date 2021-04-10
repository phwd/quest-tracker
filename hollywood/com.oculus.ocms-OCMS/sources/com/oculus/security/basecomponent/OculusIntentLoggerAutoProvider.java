package com.oculus.security.basecomponent;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusIntentLoggerAutoProvider extends AbstractProvider<OculusIntentLogger> {
    @Override // javax.inject.Provider
    public OculusIntentLogger get() {
        return new OculusIntentLogger(this);
    }
}
