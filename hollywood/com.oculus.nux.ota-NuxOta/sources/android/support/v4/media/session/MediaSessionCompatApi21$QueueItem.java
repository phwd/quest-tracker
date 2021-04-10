package android.support.v4.media.session;

import android.media.session.MediaSession;

/* access modifiers changed from: package-private */
public class MediaSessionCompatApi21$QueueItem {
    public static Object getDescription(Object obj) {
        return ((MediaSession.QueueItem) obj).getDescription();
    }

    public static long getQueueId(Object obj) {
        return ((MediaSession.QueueItem) obj).getQueueId();
    }
}
