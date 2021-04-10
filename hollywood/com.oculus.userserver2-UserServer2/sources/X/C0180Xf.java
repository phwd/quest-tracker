package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.Xf  reason: case insensitive filesystem */
public final class C0180Xf {
    public static final Executor A06 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new XE("OkHttp ConnectionPool"));
    public boolean A00;
    public final int A01 = 5;
    public final long A02;
    public final Runnable A03 = new RunnableC0181Xg(this);
    public final Deque<EX> A04 = new ArrayDeque();
    public final X4 A05 = new X4();

    public C0180Xf() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.A02 = timeUnit.toNanos(5);
    }
}
