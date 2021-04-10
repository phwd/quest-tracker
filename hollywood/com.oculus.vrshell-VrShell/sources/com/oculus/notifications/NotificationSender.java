package com.oculus.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.oculus.common.notifications.R;
import com.oculus.notifications.NotificationConstants;

public class NotificationSender {
    private static final String KEY_TEST_NOTIF_TAG = "vrshell_test_notif_tag";
    private static final String NOTIFICATION_CHANNEL_NAME = "ShellNotifications";
    public static int sNextStorageNotification = 8;

    public static void sendTestNotif(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("title", "[Missing Title]");
        String string2 = extras.getString("text", "[Missing Description]");
        int i = extras.getInt("id", 0);
        int i2 = extras.getInt(NotificationConstants.KEY_PRIORITY, 0);
        boolean z = extras.getBoolean(NotificationConstants.KEY_AUI_PERSIST, false);
        boolean z2 = extras.getBoolean(NotificationConstants.KEY_PREVENT_SOUND, false);
        String string3 = extras.getString(NotificationConstants.KEY_OCULUS_CATEGORY, "");
        NotificationConstants.LargeImageType largeImageType = NotificationConstants.LargeImageType.INVALID;
        NotificationsDisplayDuration fromName = NotificationsDisplayDuration.fromName(extras.getString(NotificationConstants.KEY_AUI_NOTIF_DURATION, ""));
        String string4 = extras.getString(KEY_TEST_NOTIF_TAG, null);
        int i3 = R.drawable.ic_home;
        Intent intent2 = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent2.setPackage("com.oculus.vrshell");
        intent2.putExtra("intent_data", Uri.parse("systemux://browser"));
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent2, 0);
        Intent intent3 = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent3.setPackage("com.oculus.vrshell");
        intent3.putExtra("intent_data", Uri.parse("systemux://browser"));
        intent3.putExtra("uri", "ovrweb://vr?uri=https%3A%2F%2Fartyom17.github.io%2Fwebvr.info%2Fsamples%2F03b-vr-navigation.html");
        build("oculus_mobile_notifications_test", string, string2, string4, i, i3).setPriority(i2).setContentIntent(broadcast).setAcceptIntent(PendingIntent.getBroadcast(context, 989, intent3, 0)).setIsPersistent(z).setDisplayDuration(fromName).setOculusCategory(string3).setIconImageType(largeImageType).setSuppressSound(z2).send(context);
    }

    public static Builder build(String str, String str2, String str3, String str4, int i, int i2) {
        return new Builder(str, str2, str3, str4, i, i2);
    }

    public static class Builder {
        PendingIntent acceptIntent = null;
        String analyticsId;
        PendingIntent contentIntent = null;
        NotificationsDisplayDuration displayDuration = NotificationsDisplayDuration.DEFAULT;
        NotificationConstants.LargeImageType iconImageType = NotificationConstants.LargeImageType.INVALID;
        int id;
        boolean isPersistent = false;
        int notificationIcon;
        String oculusCategory = null;
        int priority = 1;
        boolean suppressSound;
        String tag;
        String text;
        String title;

        public Builder(String str, String str2, String str3, String str4, int i, int i2) {
            this.analyticsId = str;
            this.title = str2;
            this.text = str3;
            this.tag = str4;
            this.notificationIcon = i2;
            if (i == -1) {
                int i3 = NotificationSender.sNextStorageNotification;
                NotificationSender.sNextStorageNotification = i3 + 1;
                this.id = i3;
                return;
            }
            this.id = i;
        }

        public Builder setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.contentIntent = pendingIntent;
            return this;
        }

        public Builder setAcceptIntent(PendingIntent pendingIntent) {
            this.acceptIntent = pendingIntent;
            return this;
        }

        public Builder setIsPersistent(boolean z) {
            this.isPersistent = z;
            return this;
        }

        public Builder setDisplayDuration(NotificationsDisplayDuration notificationsDisplayDuration) {
            this.displayDuration = notificationsDisplayDuration;
            return this;
        }

        public Builder setOculusCategory(String str) {
            this.oculusCategory = str;
            return this;
        }

        public Builder setIconImageType(NotificationConstants.LargeImageType largeImageType) {
            this.iconImageType = largeImageType;
            return this;
        }

        public Builder setSuppressSound(boolean z) {
            this.suppressSound = z;
            return this;
        }

        public int send(Context context) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationConstants.EXTRA_TYPE, this.analyticsId);
            bundle.putBoolean(NotificationConstants.KEY_AUI_PERSIST, this.isPersistent);
            bundle.putString(NotificationConstants.KEY_AUI_NOTIF_DURATION, this.displayDuration.name());
            bundle.putString(NotificationConstants.KEY_LARGE_IMAGE_TYPE, this.iconImageType.name());
            bundle.putBoolean(NotificationConstants.KEY_PREVENT_SOUND, this.suppressSound);
            String str = this.oculusCategory;
            if (str != null) {
                bundle.putString(NotificationConstants.KEY_OCULUS_CATEGORY, str);
            }
            if (this.contentIntent != null) {
                bundle.putBoolean(NotificationConstants.KEY_ACTION_ON_CLICK, true);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Build.VERSION.SDK_INT >= 26 ? NotificationSender.ensureNotificationChannel(context) : "0");
            builder.setContentTitle(this.title).setContentText(this.text).setPriority(this.priority).setSmallIcon(this.notificationIcon).setAutoCancel(true).addExtras(bundle);
            PendingIntent pendingIntent = this.contentIntent;
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            PendingIntent pendingIntent2 = this.acceptIntent;
            if (pendingIntent2 != null) {
                builder.addAction(-1, "accept", pendingIntent2);
            }
            ((NotificationManager) context.getSystemService("notification")).notify(this.tag, this.id, builder.build());
            return this.id;
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(26)
    public static String ensureNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(context.getPackageName(), NOTIFICATION_CHANNEL_NAME, 3);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notificationChannel.getId();
    }
}
