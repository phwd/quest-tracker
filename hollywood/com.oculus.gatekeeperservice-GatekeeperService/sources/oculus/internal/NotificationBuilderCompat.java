package oculus.internal;

import android.app.Notification;
import android.content.Context;

public class NotificationBuilderCompat implements NotificationBuilderInterface {
    @Override // oculus.internal.NotificationBuilderInterface
    public Notification.Builder getBuilder(Context context, String channelId) {
        return new Notification.Builder(context, channelId);
    }
}
