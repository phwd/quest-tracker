package com.oculus.common.init;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AppInitLockMethodAutoProvider extends AbstractProvider<AppInitLock> {
    @Override // javax.inject.Provider
    public AppInitLock get() {
        return AppInitModule.provideAppInitLock();
    }
}
