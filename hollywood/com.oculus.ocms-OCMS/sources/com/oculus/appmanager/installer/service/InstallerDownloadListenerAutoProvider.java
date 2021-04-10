package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerDownloadListenerAutoProvider extends AbstractProvider<InstallerDownloadListener> {
    @Override // javax.inject.Provider
    public InstallerDownloadListener get() {
        return new InstallerDownloadListener(this);
    }
}
