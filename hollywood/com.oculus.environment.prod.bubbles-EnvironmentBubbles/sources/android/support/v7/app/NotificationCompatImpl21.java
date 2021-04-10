package android.support.v7.app;

import android.app.Notification;
import android.media.session.MediaSession;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;

class NotificationCompatImpl21 {
    NotificationCompatImpl21() {
    }

    public static void addMediaStyle(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, int[] iArr, Object obj) {
        Notification.MediaStyle mediaStyle = new Notification.MediaStyle(notificationBuilderWithBuilderAccessor.getBuilder());
        if (iArr != null) {
            mediaStyle.setShowActionsInCompactView(iArr);
        }
        if (obj != null) {
            mediaStyle.setMediaSession((MediaSession.Token) obj);
        }
    }
}
