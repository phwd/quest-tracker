package com.oculus.horizon.logging;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusLoggerAutoProvider extends AbstractProvider<OculusLogger> {
    @Override // javax.inject.Provider
    public OculusLogger get() {
        return new OculusLogger(this);
    }
}
