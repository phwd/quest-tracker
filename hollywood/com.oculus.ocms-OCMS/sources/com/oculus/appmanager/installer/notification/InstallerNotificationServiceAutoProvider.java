package com.oculus.appmanager.installer.notification;

import com.facebook.inject.AbstractComponentProvider;

public class InstallerNotificationServiceAutoProvider extends AbstractComponentProvider<InstallerNotificationService> {
    public void inject(InstallerNotificationService installerNotificationService) {
        InstallerNotificationService._UL_staticInjectMe(this, installerNotificationService);
    }

    public boolean equals(Object obj) {
        return obj instanceof InstallerNotificationServiceAutoProvider;
    }
}
