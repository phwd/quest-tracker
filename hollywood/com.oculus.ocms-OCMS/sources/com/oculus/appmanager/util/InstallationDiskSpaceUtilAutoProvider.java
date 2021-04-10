package com.oculus.appmanager.util;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallationDiskSpaceUtilAutoProvider extends AbstractProvider<InstallationDiskSpaceUtil> {
    @Override // javax.inject.Provider
    public InstallationDiskSpaceUtil get() {
        return new InstallationDiskSpaceUtil(this);
    }
}
