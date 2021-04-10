package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

public abstract class OneTimeNotificationSourceHandler implements INotificationSourceHandler {
    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationSourceHandler
    public boolean acceptsListeners() {
        return false;
    }
}
