package android.support.v4.media.session;

import android.media.session.MediaSession;

/* access modifiers changed from: package-private */
public class MediaSessionCompatApi21 {
    public static Object verifyToken(Object obj) {
        if (obj instanceof MediaSession.Token) {
            return obj;
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }

    /* access modifiers changed from: package-private */
    public static class QueueItem {
        public static Object getDescription(Object obj) {
            return ((MediaSession.QueueItem) obj).getDescription();
        }

        public static long getQueueId(Object obj) {
            return ((MediaSession.QueueItem) obj).getQueueId();
        }
    }
}
