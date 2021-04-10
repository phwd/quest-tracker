package com.oculus.debug;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DebugModeAutoProvider extends AbstractProvider<DebugMode> {
    @Override // javax.inject.Provider
    public DebugMode get() {
        return new DebugMode(this);
    }
}
