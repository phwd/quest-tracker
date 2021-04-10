package com.oculus.appmanager.installer.common;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerFileUtilsAutoProvider extends AbstractProvider<InstallerFileUtils> {
    @Override // javax.inject.Provider
    public InstallerFileUtils get() {
        return new InstallerFileUtils(this);
    }
}
