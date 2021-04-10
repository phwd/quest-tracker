package com.oculus.common.init.impl;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AppInitializerAutoProvider extends AbstractProvider<AppInitializer> {
    @Override // javax.inject.Provider
    public AppInitializer get() {
        return new AppInitializer(this);
    }
}
