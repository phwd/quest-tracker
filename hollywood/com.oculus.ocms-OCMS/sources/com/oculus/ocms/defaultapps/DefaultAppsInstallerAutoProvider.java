package com.oculus.ocms.defaultapps;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DefaultAppsInstallerAutoProvider extends AbstractProvider<DefaultAppsInstaller> {
    @Override // javax.inject.Provider
    public DefaultAppsInstaller get() {
        return new DefaultAppsInstaller(this);
    }
}
