package android.support.v4.app;

import android.app.Notification;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.GROUP_ID})
public interface NotificationBuilderWithBuilderAccessor {
    Notification build();

    Notification.Builder getBuilder();
}
