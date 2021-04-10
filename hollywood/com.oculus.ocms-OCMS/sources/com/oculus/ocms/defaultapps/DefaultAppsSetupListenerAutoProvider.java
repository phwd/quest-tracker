package com.oculus.ocms.defaultapps;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DefaultAppsSetupListenerAutoProvider extends AbstractProvider<DefaultAppsSetupListener> {
    @Override // javax.inject.Provider
    public DefaultAppsSetupListener get() {
        return new DefaultAppsSetupListener(this);
    }
}
