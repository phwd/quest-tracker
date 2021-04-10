package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.bW  reason: case insensitive filesystem */
public final class C0533bW {
    public static final Executor A06 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC0560bx("OkHttp ConnectionPool", true));
    public boolean A00;
    public final int A01 = 5;
    public final long A02;
    public final Runnable A03 = new RunnableC0532bV(this);
    public final Deque A04 = new ArrayDeque();
    public final c5 A05 = new c5();

    public C0533bW() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.A02 = timeUnit.toNanos(5);
    }
}
