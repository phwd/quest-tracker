package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallQueueAutoProvider extends AbstractProvider<InstallQueue> {
    @Override // javax.inject.Provider
    public InstallQueue get() {
        return new InstallQueue(this);
    }
}
