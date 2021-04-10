package android.support.v4.media;

import android.annotation.TargetApi;
import android.os.IBinder;

@TargetApi(19)
public class MediaSession2 implements MediaInterface2$SessionPlayer, AutoCloseable {
    private final SupportLibraryImpl mImpl;

    /* access modifiers changed from: package-private */
    public interface SupportLibraryImpl extends MediaInterface2$SessionPlayer, AutoCloseable {
        IBinder getSessionBinder();

        SessionToken2 getToken();
    }

    /* access modifiers changed from: package-private */
    public SupportLibraryImpl getImpl() {
        return this.mImpl;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            this.mImpl.close();
        } catch (Exception unused) {
        }
    }

    public SessionToken2 getToken() {
        return this.mImpl.getToken();
    }

    /* access modifiers changed from: package-private */
    public IBinder getSessionBinder() {
        return this.mImpl.getSessionBinder();
    }
}
