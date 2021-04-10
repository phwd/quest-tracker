package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerRetryHelperAutoProvider extends AbstractProvider<InstallerRetryHelper> {
    @Override // javax.inject.Provider
    public InstallerRetryHelper get() {
        return new InstallerRetryHelper(this);
    }
}
