package android.support.v4.media;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.MediaSessionService2;
import android.util.Log;

/* access modifiers changed from: package-private */
public class MediaSessionService2ImplBase implements MediaSessionService2.SupportLibraryImpl {
    private final Object mLock = new Object();
    private MediaSession2 mSession;

    public int getSessionType() {
        return 1;
    }

    MediaSessionService2ImplBase() {
    }

    @Override // android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public void onCreate(MediaSessionService2 mediaSessionService2) {
        SessionToken2 sessionToken2 = new SessionToken2(mediaSessionService2, new ComponentName(mediaSessionService2, mediaSessionService2.getClass().getName()));
        if (sessionToken2.getType() == getSessionType()) {
            MediaSession2 onCreateSession = mediaSessionService2.onCreateSession(sessionToken2.getId());
            synchronized (this.mLock) {
                this.mSession = onCreateSession;
                if (this.mSession == null || !sessionToken2.getId().equals(this.mSession.getToken().getId()) || this.mSession.getToken().getType() != getSessionType()) {
                    this.mSession = null;
                    throw new RuntimeException("Expected session with id " + sessionToken2.getId() + " and type " + sessionToken2.getType() + ", but got " + this.mSession);
                }
            }
            return;
        }
        throw new RuntimeException("Expected session type " + getSessionType() + " but was " + sessionToken2.getType());
    }

    @Override // android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public IBinder onBind(Intent intent) {
        if (!"android.media.MediaSessionService2".equals(intent.getAction())) {
            return null;
        }
        synchronized (this.mLock) {
            if (this.mSession != null) {
                return this.mSession.getSessionBinder();
            }
            Log.d("MSS2ImplBase", "Session hasn't created");
            return null;
        }
    }

    @Override // android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public MediaSession2 getSession() {
        MediaSession2 mediaSession2;
        synchronized (this.mLock) {
            mediaSession2 = this.mSession;
        }
        return mediaSession2;
    }
}
