package com.oculus.notifications;

import android.service.notification.StatusBarNotification;

public interface NotificationActionListener {
    void onNotificationClicked(StatusBarNotification statusBarNotification);
}
