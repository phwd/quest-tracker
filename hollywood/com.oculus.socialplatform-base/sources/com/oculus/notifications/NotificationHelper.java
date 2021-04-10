package com.oculus.notifications;

import android.content.Context;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.vrshell.notifications.INotificationDataSetProvider;

public class NotificationHelper {
    public static final String TAG = LoggingUtil.tag(NotificationHelper.class);

    public static long getNotificationDisplayDurationMs(StatusBarNotification statusBarNotification) {
        return (long) (NotificationsDisplayDuration.fromName(statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_AUI_NOTIF_DURATION, "")).length * 1000);
    }

    public static long getTimeOfPostMs(StatusBarNotification statusBarNotification) {
        long j = statusBarNotification.getNotification().extras.getLong("original_post_time", -1);
        if (j == -1) {
            return statusBarNotification.getPostTime();
        }
        return j;
    }

    public static boolean isBlockedNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isBlockedNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static boolean isHighPriorityNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isHighPriorityNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static boolean isPersistentNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isPersistentNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static boolean isSocialNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isSocialNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static boolean isSystemNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isSystemNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public static boolean isSystemPersistentNotification(Context context, StatusBarNotification statusBarNotification) {
        INotificationDataSetProvider iNotificationDataSetProvider = NotificationDataSetService.Loader.getInstance().mDataSetProvider;
        if (iNotificationDataSetProvider == null) {
            return false;
        }
        try {
            return iNotificationDataSetProvider.isSystemPersistentNotification(statusBarNotification);
        } catch (RemoteException unused) {
            return false;
        }
    }
}
