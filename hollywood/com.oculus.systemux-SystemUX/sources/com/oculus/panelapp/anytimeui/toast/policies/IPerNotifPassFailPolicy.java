package com.oculus.panelapp.anytimeui.toast.policies;

import android.service.notification.StatusBarNotification;

public interface IPerNotifPassFailPolicy {
    void destroy();

    boolean evaluate(StatusBarNotification statusBarNotification);
}
