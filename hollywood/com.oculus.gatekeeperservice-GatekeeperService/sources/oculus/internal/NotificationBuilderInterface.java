package oculus.internal;

import android.app.Notification;
import android.content.Context;

public interface NotificationBuilderInterface {
    Notification.Builder getBuilder(Context context, String str);
}
