package com.oculus.appmanager.installer.dumper;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerServiceDumperAutoProvider extends AbstractProvider<InstallerServiceDumper> {
    @Override // javax.inject.Provider
    public InstallerServiceDumper get() {
        return new InstallerServiceDumper(this);
    }
}
