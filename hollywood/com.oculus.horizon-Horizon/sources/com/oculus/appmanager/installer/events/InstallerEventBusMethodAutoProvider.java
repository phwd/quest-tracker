package com.oculus.appmanager.installer.events;

import X.AnonymousClass0J3;
import com.facebook.annotations.Generated;

@Generated({"By: InjectorProcessor"})
public class InstallerEventBusMethodAutoProvider extends AnonymousClass0J3<InstallerEventBus> {
    public final Object get() {
        InstallerEventBus installerEventBus;
        synchronized (InstallerEventBus.class) {
            installerEventBus = InstallerEventBus.sInstance;
            if (installerEventBus == null) {
                installerEventBus = new InstallerEventBus();
                InstallerEventBus.sInstance = installerEventBus;
            }
        }
        return installerEventBus;
    }
}
