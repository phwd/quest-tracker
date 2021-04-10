package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerServiceManagerAutoProvider extends AbstractProvider<InstallerServiceManager> {
    @Override // javax.inject.Provider
    public InstallerServiceManager get() {
        return new InstallerServiceManager(this);
    }
}
