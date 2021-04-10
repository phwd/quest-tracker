package oculus.internal;

import android.app.Notification;
import android.content.Context;

public interface NotificationChannelInterface {
    void setChannelIdForNotification(String str, Notification.Builder builder);

    void setupNotificationChannel(Context context, String str, String str2, String str3);
}
