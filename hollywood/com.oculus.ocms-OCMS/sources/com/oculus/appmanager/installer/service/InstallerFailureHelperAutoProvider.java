package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerFailureHelperAutoProvider extends AbstractProvider<InstallerFailureHelper> {
    @Override // javax.inject.Provider
    public InstallerFailureHelper get() {
        return new InstallerFailureHelper(this);
    }
}
