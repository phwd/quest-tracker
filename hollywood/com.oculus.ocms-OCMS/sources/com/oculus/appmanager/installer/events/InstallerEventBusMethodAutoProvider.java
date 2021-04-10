package com.oculus.appmanager.installer.events;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerEventBusMethodAutoProvider extends AbstractProvider<InstallerEventBus> {
    @Override // javax.inject.Provider
    public InstallerEventBus get() {
        return EventsModule.providerInstallerEventBus();
    }
}
