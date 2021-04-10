package android.support.v4.media;

import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.MediaSessionService2;

public abstract class MediaLibraryService2 extends MediaSessionService2 {
    @Override // android.support.v4.media.MediaSessionService2
    public abstract MediaLibrarySession onCreateSession(String str);

    public static final class MediaLibrarySession extends MediaSession2 {

        /* access modifiers changed from: package-private */
        public interface SupportLibraryImpl extends MediaSession2.SupportLibraryImpl {
            IBinder getLegacySessionBinder();
        }

        /* access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2
        public SupportLibraryImpl getImpl() {
            return (SupportLibraryImpl) super.getImpl();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v4.media.MediaSessionService2
    public MediaSessionService2.SupportLibraryImpl createImpl() {
        return new MediaLibraryService2ImplBase();
    }

    @Override // android.support.v4.media.MediaSessionService2
    public void onCreate() {
        super.onCreate();
        if (!(getSession() instanceof MediaLibrarySession)) {
            throw new RuntimeException("Expected MediaLibrarySession, but returned MediaSession2");
        }
    }

    @Override // android.support.v4.media.MediaSessionService2
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
