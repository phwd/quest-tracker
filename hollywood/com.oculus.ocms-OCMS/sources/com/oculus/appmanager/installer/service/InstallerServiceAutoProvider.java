package com.oculus.appmanager.installer.service;

import com.facebook.inject.AbstractComponentProvider;

public class InstallerServiceAutoProvider extends AbstractComponentProvider<InstallerService> {
    public void inject(InstallerService installerService) {
        InstallerService._UL_staticInjectMe(this, installerService);
    }

    public boolean equals(Object obj) {
        return obj instanceof InstallerServiceAutoProvider;
    }
}
