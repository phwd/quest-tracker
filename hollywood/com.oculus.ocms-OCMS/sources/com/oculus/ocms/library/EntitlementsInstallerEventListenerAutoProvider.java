package com.oculus.ocms.library;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class EntitlementsInstallerEventListenerAutoProvider extends AbstractProvider<EntitlementsInstallerEventListener> {
    @Override // javax.inject.Provider
    public EntitlementsInstallerEventListener get() {
        return new EntitlementsInstallerEventListener(this);
    }
}
