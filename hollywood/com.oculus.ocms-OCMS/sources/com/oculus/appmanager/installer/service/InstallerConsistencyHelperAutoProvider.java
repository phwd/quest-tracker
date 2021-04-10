package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerConsistencyHelperAutoProvider extends AbstractProvider<InstallerConsistencyHelper> {
    @Override // javax.inject.Provider
    public InstallerConsistencyHelper get() {
        return new InstallerConsistencyHelper(this);
    }
}
