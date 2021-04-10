package com.oculus.common.socialtablet.notif;

import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

public class SocialNotificationUtils {
    public static final String MESSENGER_NOTIFICATION_TYPE = "oculus_vr_messaging_new_message";
    public static final String NOTIF_TYPE_FIELD = "oculus_notification_type";
    public static final String NOTIF_XTYPE_FIELD = "oculus_notification_xnotiftype";
    public static final String OCULUS_CHATS_NOTIFICATION_TYPE = "oculus_messaging_new_message";
    public static final String TAG = "SocialNotificationUtils";

    public static String getNotificationType(StatusBarNotification statusBarNotification) {
        String str;
        String str2 = "";
        try {
            str = statusBarNotification.getNotification().extras.getString("oculus_notification_type");
        } catch (NullPointerException e) {
            Log.e(TAG, "No value for notif type field", e);
            str = str2;
        }
        try {
            str2 = statusBarNotification.getNotification().extras.getString(NOTIF_XTYPE_FIELD);
        } catch (NullPointerException e2) {
            Log.e(TAG, "No value for xnotif type field", e2);
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str2;
    }

    public static boolean isFBMessengerNotification(StatusBarNotification statusBarNotification) {
        return MESSENGER_NOTIFICATION_TYPE.equals(getNotificationType(statusBarNotification));
    }

    public static boolean isMessagingNotification(StatusBarNotification statusBarNotification) {
        if (isFBMessengerNotification(statusBarNotification) || isOculusChatsNotification(statusBarNotification)) {
            return true;
        }
        return false;
    }

    public static boolean isOculusChatsNotification(StatusBarNotification statusBarNotification) {
        return OCULUS_CHATS_NOTIFICATION_TYPE.equals(getNotificationType(statusBarNotification));
    }
}
