package com.oculus.vrshell.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationsDisplayDuration;
import com.oculus.os.ActivityManagerUtils;

public class VrNotificationToastGenerator {
    public static final String ACTION_DISMISS_NOTIFICATION = "com.oculus.vrshell.intent.action.DISMISS_NOTIFICATION";
    public static final String ACTION_TOAST_NOTIFICATION = "com.oculus.vrshell.intent.action.TOAST_NOTIFICATION";
    public static final String KEY_TOAST_NOTIFICATION_FOREGROUND_APP = "foreground_package_name";
    public static final String KEY_TOAST_NOTIFICATION_IS_SYSTEM = "is_system_notification";
    public static final String KEY_TOAST_NOTIFICATION_STATUS_BAR_NOTIFICATION = "status_bar_notification";
    public static final String KEY_TOAST_NOTIFICATION_TOAST_DURATION = "toast_duration";
    public static final String KEY_TOAST_NOTIFICATION_TOAST_HAS_CTA = "toast_has_cta";
    private static final String TAG = LoggingUtil.tag(VrNotificationToastGenerator.class);

    public static void sendNotificationToast(StatusBarNotification statusBarNotification, Context context, boolean z, boolean z2, boolean z3) {
        int i = NotificationsDisplayDuration.fromName(statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_AUI_NOTIF_DURATION, "")).length;
        String foregroundApp = new ActivityManagerUtils().getForegroundApp(context);
        Intent intent = new Intent(ACTION_TOAST_NOTIFICATION);
        intent.setPackage("com.oculus.vrshell");
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOAST_NOTIFICATION_STATUS_BAR_NOTIFICATION, statusBarNotification.getKey());
        bundle.putBoolean(KEY_TOAST_NOTIFICATION_IS_SYSTEM, z2);
        bundle.putString(KEY_TOAST_NOTIFICATION_FOREGROUND_APP, foregroundApp);
        bundle.putInt(KEY_TOAST_NOTIFICATION_TOAST_DURATION, i);
        bundle.putBoolean(KEY_TOAST_NOTIFICATION_TOAST_HAS_CTA, z3 && z);
        bundle.putString(NotificationConstants.KEY_OCULUS_BUTTON_OVERRIDE_URI, getOculusButtonOverride(statusBarNotification));
        intent.putExtras(bundle);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            Log.e(TAG, "Error sending system ux toast broadcast", e);
        }
    }

    public static String getOculusButtonOverride(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_OCULUS_BUTTON_OVERRIDE_URI, "");
    }

    public static void dismissNotificationToast(StatusBarNotification statusBarNotification, Context context) {
        Intent intent = new Intent(ACTION_DISMISS_NOTIFICATION);
        intent.setPackage("com.oculus.vrshell");
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOAST_NOTIFICATION_STATUS_BAR_NOTIFICATION, statusBarNotification.getKey());
        intent.putExtras(bundle);
        try {
            context.sendBroadcast(intent);
        } catch (Exception e) {
            Log.e(TAG, "Error sending system ux dismiss broadcast", e);
        }
    }
}
