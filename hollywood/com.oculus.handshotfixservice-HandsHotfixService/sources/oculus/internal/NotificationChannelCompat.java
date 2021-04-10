package oculus.internal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.text.TextUtils;

public class NotificationChannelCompat implements NotificationChannelInterface {
    @Override // oculus.internal.NotificationChannelInterface
    public void setupNotificationChannel(Context context, String channelId, String name, String description) {
        if (!TextUtils.isEmpty(channelId) && !TextUtils.isEmpty(name)) {
            NotificationChannel channel = new NotificationChannel(channelId, name, 3);
            channel.setDescription(description);
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(channel);
        }
    }

    @Override // oculus.internal.NotificationChannelInterface
    public void setChannelIdForNotification(String channelId, Notification.Builder b) {
        b.setChannelId(channelId);
    }

    @Override // oculus.internal.NotificationChannelInterface
    public void deleteNotificationChannel(Context context, String channelId) {
        ((NotificationManager) context.getSystemService("notification")).deleteNotificationChannel(channelId);
    }
}
