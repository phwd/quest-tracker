package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import java.util.List;

public interface INotificationSourceHandler<T extends IBaseVRNotification> {
    boolean acceptsListeners();

    List<T> initialFetch();
}
