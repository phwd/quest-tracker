package com.oculus.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.oculus.notifications.NotificationConstants;

public class NotificationSender {
    private static final String NOTIFICATION_CHANNEL_NAME = "ShellNotifications";
    public static int sNextStorageNotification = 1;

    public static class Builder {
        private PendingIntent mAcceptIntent = null;
        private final String mAnalyticsId;
        private PendingIntent mContentIntent = null;
        private NotificationsDisplayDuration mDisplayDuration = NotificationsDisplayDuration.DEFAULT;
        private final int mIcon;
        private NotificationConstants.LargeImageType mIconImageType = NotificationConstants.LargeImageType.ICON;
        private int mId = -1;
        private boolean mIsPersistent = false;
        private String mOculusCategory = null;
        private int mPriority = 1;
        private String mTag = null;
        private final String mText;
        private final String mTitle;

        public Builder(String str, String str2, String str3, int i) {
            this.mAnalyticsId = str;
            this.mTitle = str2;
            this.mText = str3;
            this.mIcon = i;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setAcceptIntent(PendingIntent pendingIntent) {
            this.mAcceptIntent = pendingIntent;
            return this;
        }

        public Builder setIsPersistent(boolean z) {
            this.mIsPersistent = z;
            return this;
        }

        public Builder setDisplayDuration(NotificationsDisplayDuration notificationsDisplayDuration) {
            this.mDisplayDuration = notificationsDisplayDuration;
            return this;
        }

        public Builder setOculusCategory(String str) {
            this.mOculusCategory = str;
            return this;
        }

        public Builder setIconImageType(NotificationConstants.LargeImageType largeImageType) {
            this.mIconImageType = largeImageType;
            return this;
        }

        public void send(Context context) {
            int i = this.mId;
            if (i == -1) {
                i = NotificationSender.sNextStorageNotification;
                NotificationSender.sNextStorageNotification = i + 1;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean(NotificationConstants.KEY_AUI_PERSIST, this.mIsPersistent);
            bundle.putString(NotificationConstants.KEY_AUI_NOTIF_DURATION, this.mDisplayDuration.name());
            bundle.putString(NotificationConstants.KEY_OCULUS_CATEGORY, this.mOculusCategory);
            bundle.putString(NotificationConstants.KEY_LARGE_IMAGE_TYPE, this.mIconImageType.name());
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Build.VERSION.SDK_INT >= 26 ? NotificationSender.ensureNotificationChannel(context) : "0");
            builder.setContentTitle(this.mTitle).setContentText(this.mText).setPriority(this.mPriority).setSmallIcon(this.mIcon).setAutoCancel(true).addExtras(bundle);
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            PendingIntent pendingIntent2 = this.mAcceptIntent;
            if (pendingIntent2 != null) {
                builder.addAction(-1, "accept", pendingIntent2);
            }
            ((NotificationManager) context.getSystemService("notification")).notify(this.mTag, i, builder.build());
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

    public static Builder build(String str, String str2, String str3, int i) {
        return new Builder(str, str2, str3, i);
    }
}
