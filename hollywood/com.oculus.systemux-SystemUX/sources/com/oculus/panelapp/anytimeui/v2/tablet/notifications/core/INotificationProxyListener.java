package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

public interface INotificationProxyListener {
    void onNotificationAdded(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification);

    void onNotificationRemoved(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification);

    void onNotificationUpdated(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification);
}
