package com.facebook.drawee.components;

import android.os.Looper;
import javax.annotation.Nullable;

public abstract class DeferredReleaser {
    @Nullable
    private static DeferredReleaser sInstance;

    public interface Releasable {
        void release();
    }

    public abstract void cancelDeferredRelease(Releasable releasable);

    public abstract void scheduleDeferredRelease(Releasable releasable);

    public static synchronized DeferredReleaser getInstance() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            if (sInstance == null) {
                sInstance = new DeferredReleaserConcurrentImpl();
            }
            deferredReleaser = sInstance;
        }
        return deferredReleaser;
    }

    static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
