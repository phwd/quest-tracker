package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class MediaSessionService2 extends Service {
    private final SupportLibraryImpl mImpl = createImpl();

    interface SupportLibraryImpl {
        MediaSession2 getSession();

        IBinder onBind(Intent intent);

        void onCreate(MediaSessionService2 mediaSessionService2);
    }

    public abstract MediaSession2 onCreateSession(String str);

    /* access modifiers changed from: package-private */
    public SupportLibraryImpl createImpl() {
        return new MediaSessionService2ImplBase();
    }

    public void onCreate() {
        super.onCreate();
        this.mImpl.onCreate(this);
    }

    public final MediaSession2 getSession() {
        return this.mImpl.getSession();
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }
}
