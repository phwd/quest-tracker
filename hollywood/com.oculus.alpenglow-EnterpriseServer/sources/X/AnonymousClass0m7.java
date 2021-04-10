package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0m7  reason: invalid class name */
public final class AnonymousClass0m7 {
    public static final Executor A06 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC05580k0("OkHttp ConnectionPool", true));
    public boolean A00;
    public final int A01 = 5;
    public final long A02;
    public final Runnable A03 = new RunnableC06300mW(this);
    public final Deque<C01990Pw> A04 = new ArrayDeque();
    public final C05410jH A05 = new C05410jH();

    public AnonymousClass0m7() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.A02 = timeUnit.toNanos(5);
    }
}
