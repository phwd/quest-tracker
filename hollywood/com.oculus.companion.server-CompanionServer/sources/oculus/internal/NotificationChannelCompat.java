package oculus.internal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;

public class NotificationChannelCompat implements NotificationChannelInterface {
    @Override // oculus.internal.NotificationChannelInterface
    public void setupNotificationChannel(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 3);
            notificationChannel.setDescription(str3);
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(notificationChannel);
        }
    }

    @Override // oculus.internal.NotificationChannelInterface
    public void setChannelIdForNotification(String str, Notification.Builder builder) {
        builder.setChannelId(str);
    }
}
