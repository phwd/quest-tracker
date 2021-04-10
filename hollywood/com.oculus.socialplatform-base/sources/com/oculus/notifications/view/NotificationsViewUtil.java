package com.oculus.notifications.view;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.socialplatform.R;

public class NotificationsViewUtil {
    public static NotificationView getContentViewForNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationView notificationView = (NotificationView) LayoutInflater.from(context).inflate(R.layout.notification_generic, (ViewGroup) null);
        notificationView.initialize(statusBarNotification);
        return notificationView;
    }
}
