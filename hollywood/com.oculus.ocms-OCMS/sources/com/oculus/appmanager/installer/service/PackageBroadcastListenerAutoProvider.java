package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class PackageBroadcastListenerAutoProvider extends AbstractProvider<PackageBroadcastListener> {
    @Override // javax.inject.Provider
    public PackageBroadcastListener get() {
        return new PackageBroadcastListener(this);
    }
}
