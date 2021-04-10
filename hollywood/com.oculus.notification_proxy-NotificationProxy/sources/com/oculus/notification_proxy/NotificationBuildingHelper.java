package com.oculus.notification_proxy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import oculus.internal.NotificationChannelCompat;

public class NotificationBuildingHelper {
    private static final String CHANNEL_ID = "com.oculus.notification_proxy.notif_channel";
    private static final String KEY_PREVENT_SOUND = "prevent_sound";
    private static final NotificationChannelCompat sNotificationChannel = new NotificationChannelCompat();

    static void setup(Context context) {
        sNotificationChannel.setupNotificationChannel(context, CHANNEL_ID, "NotificationProxy", "Service that generates Oculus system notifications");
    }

    static void notify(Context context, String str, int i, String str2, String str3, int i2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", context.getString(R.string.notification_header));
        bundle.putBoolean(KEY_PREVENT_SOUND, z);
        Notification.Builder builder = new Notification.Builder(context);
        Notification.Builder extras = builder.setSmallIcon(17301543).setContentTitle(str2).setStyle(new Notification.BigTextStyle().bigText(str3)).setPriority(i2).setExtras(bundle);
        extras.setGroup("unique" + i);
        sNotificationChannel.setChannelIdForNotification(CHANNEL_ID, builder);
        ((NotificationManager) context.getSystemService("notification")).notifyAsUser(str, i, builder.build(), UserHandle.CURRENT);
    }

    static void notify(Context context, String str, int i, String str2, String str3, int i2) {
        notify(context, str, i, str2, str3, i2, false);
    }

    static void notify(Context context, String str, int i, String str2, String str3) {
        notify(context, str, i, str2, str3, 2);
    }

    static void notify(Context context, int i, int i2, String str, int i3) {
        notify(context, str, i3, context.getString(i), context.getString(i2));
    }

    static void notify(Context context, int i, int i2, int i3) {
        notify(context, i, i2, (String) null, i3);
    }

    static void notifyFormatText(Context context, int i, int i2, String str, int i3, boolean z, Object... objArr) {
        notify(context, str, i3, context.getString(i), context.getString(i2, objArr), 2, z);
    }
}
