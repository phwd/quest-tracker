package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerServiceInstallLockAutoProvider extends AbstractProvider<InstallerServiceInstallLock> {
    @Override // javax.inject.Provider
    public InstallerServiceInstallLock get() {
        return new InstallerServiceInstallLock(this);
    }
}
