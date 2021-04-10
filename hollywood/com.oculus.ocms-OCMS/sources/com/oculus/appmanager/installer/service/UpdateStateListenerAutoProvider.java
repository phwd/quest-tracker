package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class UpdateStateListenerAutoProvider extends AbstractProvider<UpdateStateListener> {
    @Override // javax.inject.Provider
    public UpdateStateListener get() {
        return new UpdateStateListener(this);
    }
}
