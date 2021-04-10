package oculus.internal;

import android.app.Notification;
import android.content.Context;

public class NotificationBuilderCompat implements NotificationBuilderInterface {
    public Notification.Builder getBuilder(Context context, String str) {
        return new Notification.Builder(context, str);
    }
}
