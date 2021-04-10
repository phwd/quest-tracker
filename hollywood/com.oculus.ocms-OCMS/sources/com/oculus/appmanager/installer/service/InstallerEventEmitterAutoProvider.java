package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerEventEmitterAutoProvider extends AbstractProvider<InstallerEventEmitter> {
    @Override // javax.inject.Provider
    public InstallerEventEmitter get() {
        return new InstallerEventEmitter(this);
    }
}
