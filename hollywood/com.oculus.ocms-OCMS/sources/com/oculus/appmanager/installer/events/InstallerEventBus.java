package com.oculus.appmanager.installer.events;

import com.oculus.appmanager.eventbus.BaseEventBus;

public class InstallerEventBus extends BaseEventBus {
    private static InstallerEventBus sInstance;

    private InstallerEventBus() {
    }

    public static synchronized InstallerEventBus getInstance() {
        InstallerEventBus installerEventBus;
        synchronized (InstallerEventBus.class) {
            if (sInstance == null) {
                sInstance = new InstallerEventBus();
            }
            installerEventBus = sInstance;
        }
        return installerEventBus;
    }
}
