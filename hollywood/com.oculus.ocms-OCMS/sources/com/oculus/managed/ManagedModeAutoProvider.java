package com.oculus.managed;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ManagedModeAutoProvider extends AbstractProvider<ManagedMode> {
    @Override // javax.inject.Provider
    public ManagedMode get() {
        return new ManagedMode(this);
    }
}
