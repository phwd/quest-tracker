package com.oculus.notifications;

import android.content.Context;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public class NotificationHelper {
    private static final String TAG = LoggingUtil.tag(NotificationHelper.class);

    public static boolean isSystemNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isSystemNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static boolean isSocialNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isSocialNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static boolean isSystemPersistentNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isSystemPersistentNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static boolean isHighPriorityNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isHighPriorityNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static boolean isBlockedNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isBlockedNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static boolean isPersistentNotification(Context context, StatusBarNotification statusBarNotification) {
        NotificationDataSetService instance = NotificationDataSetService.getInstance();
        if (instance == null || instance.getDataSetProvider() == null) {
            return false;
        }
        try {
            return instance.getDataSetProvider().isPersistentNotification(statusBarNotification);
        } catch (RemoteException e) {
            Log.d(TAG, "Unable to connect to notifications service.", e);
            return false;
        }
    }

    public static long getTimeOfPostMs(StatusBarNotification statusBarNotification) {
        long j = statusBarNotification.getNotification().extras.getLong("original_post_time", -1);
        return j == -1 ? statusBarNotification.getPostTime() : j;
    }

    public static long getNotificationDisplayDurationMs(StatusBarNotification statusBarNotification) {
        return (long) (NotificationsDisplayDuration.fromName(statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_AUI_NOTIF_DURATION, "")).length * 1000);
    }
}
