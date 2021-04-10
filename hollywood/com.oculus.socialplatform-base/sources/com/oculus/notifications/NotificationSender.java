package com.oculus.notifications;

import X.AnonymousClass03m;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.oculus.notifications.NotificationConstants;

public class NotificationSender {
    public static final String NOTIFICATION_CHANNEL_NAME = "ShellNotifications";
    public static int sNextStorageNotification = 1;

    public static class Builder {
        public PendingIntent mAcceptIntent = null;
        public final String mAnalyticsId;
        public PendingIntent mContentIntent = null;
        public NotificationsDisplayDuration mDisplayDuration = NotificationsDisplayDuration.DEFAULT;
        public final int mIcon;
        public NotificationConstants.LargeImageType mIconImageType = NotificationConstants.LargeImageType.ICON;
        public int mId = -1;
        public boolean mIsPersistent = false;
        public String mOculusCategory = null;
        public int mPriority = 1;
        public String mTag = null;
        public final String mText;
        public final String mTitle;

        public void send(Context context) {
            String str;
            int i = this.mId;
            if (i == -1) {
                i = NotificationSender.sNextStorageNotification;
                NotificationSender.sNextStorageNotification = i + 1;
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("vrshell_aui_persist", this.mIsPersistent);
            bundle.putString(NotificationConstants.KEY_AUI_NOTIF_DURATION, this.mDisplayDuration.name());
            bundle.putString("oculus_category", this.mOculusCategory);
            bundle.putString("large_image_type", this.mIconImageType.name());
            if (Build.VERSION.SDK_INT >= 26) {
                str = NotificationSender.ensureNotificationChannel(context);
            } else {
                str = "0";
            }
            AnonymousClass03m r4 = new AnonymousClass03m(context, str);
            r4.A0C = AnonymousClass03m.A00(this.mTitle);
            r4.A0B = AnonymousClass03m.A00(this.mText);
            r4.A05 = this.mPriority;
            int i2 = this.mIcon;
            Notification notification = r4.A06;
            notification.icon = i2;
            notification.flags = 16 | notification.flags;
            Bundle bundle2 = r4.A09;
            if (bundle2 == null) {
                r4.A09 = new Bundle(bundle);
            } else {
                bundle2.putAll(bundle);
            }
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                r4.A07 = pendingIntent;
            }
            PendingIntent pendingIntent2 = this.mAcceptIntent;
            if (pendingIntent2 != null) {
                r4.A02(-1, "accept", pendingIntent2);
            }
            ((NotificationManager) context.getSystemService("notification")).notify(this.mTag, i, r4.A01());
        }

        public Builder(String str, String str2, String str3, int i) {
            this.mAnalyticsId = str;
            this.mTitle = str2;
            this.mText = str3;
            this.mIcon = i;
        }

        public Builder setAcceptIntent(PendingIntent pendingIntent) {
            this.mAcceptIntent = pendingIntent;
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setDisplayDuration(NotificationsDisplayDuration notificationsDisplayDuration) {
            this.mDisplayDuration = notificationsDisplayDuration;
            return this;
        }

        public Builder setIconImageType(NotificationConstants.LargeImageType largeImageType) {
            this.mIconImageType = largeImageType;
            return this;
        }

        public Builder setId(int i) {
            this.mId = i;
            return this;
        }

        public Builder setIsPersistent(boolean z) {
            this.mIsPersistent = z;
            return this;
        }

        public Builder setOculusCategory(String str) {
            this.mOculusCategory = str;
            return this;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }
    }

    public static Builder build(String str, String str2, String str3, int i) {
        return new Builder(str, str2, str3, i);
    }

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
