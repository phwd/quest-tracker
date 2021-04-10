package com.oculus.appmanager.uninstaller.events;

import com.oculus.appmanager.eventbus.BaseEventBus;

public class UninstallerEventBus extends BaseEventBus {
    private static UninstallerEventBus sInstance;

    private UninstallerEventBus() {
    }

    public static synchronized UninstallerEventBus getInstance() {
        UninstallerEventBus uninstallerEventBus;
        synchronized (UninstallerEventBus.class) {
            if (sInstance == null) {
                sInstance = new UninstallerEventBus();
            }
            uninstallerEventBus = sInstance;
        }
        return uninstallerEventBus;
    }
}
