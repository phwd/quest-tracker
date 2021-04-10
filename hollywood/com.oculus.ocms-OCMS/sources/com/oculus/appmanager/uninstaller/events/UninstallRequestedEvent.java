package com.oculus.appmanager.uninstaller.events;

import com.google.common.base.MoreObjects;

public class UninstallRequestedEvent {
    public final String installerIdentifier;

    public UninstallRequestedEvent(String str) {
        this.installerIdentifier = str;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper((Class<?>) UninstallRequestedEvent.class);
        stringHelper.add("identifier", this.installerIdentifier);
        return stringHelper.toString();
    }
}
