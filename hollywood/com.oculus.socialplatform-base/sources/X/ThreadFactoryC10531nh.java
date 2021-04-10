package X;

import java.util.concurrent.ThreadFactory;

/* renamed from: X.1nh  reason: invalid class name and case insensitive filesystem */
public final class ThreadFactoryC10531nh implements ThreadFactory {
    public final synchronized Thread newThread(Runnable runnable) {
        Thread thread;
        thread = new Thread(runnable, "glide-disk-lru-cache-thread");
        thread.setPriority(1);
        return thread;
    }
}
