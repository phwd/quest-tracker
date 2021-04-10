package X;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1ap  reason: invalid class name and case insensitive filesystem */
public final class ThreadFactoryC06661ap extends AtomicLong implements ThreadFactory {
    public static final long serialVersionUID = -7789753024099756196L;
    public final boolean nonBlocking;
    public final String prefix;
    public final int priority;

    public final Thread newThread(Runnable runnable) {
        Thread thread;
        StringBuilder sb = new StringBuilder(this.prefix);
        sb.append('-');
        sb.append(incrementAndGet());
        String obj = sb.toString();
        if (this.nonBlocking) {
            thread = new C06671aq(runnable, obj);
        } else {
            thread = new Thread(runnable, obj);
        }
        thread.setPriority(this.priority);
        thread.setDaemon(true);
        return thread;
    }

    public final String toString() {
        return AnonymousClass006.A09("RxThreadFactory[", this.prefix, "]");
    }

    public ThreadFactoryC06661ap(String str, int i, boolean z) {
        this.prefix = str;
        this.priority = i;
        this.nonBlocking = z;
    }
}
