package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class e1 {
    public static final Executor A06 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC0353da("OkHttp ConnectionPool", true));
    public boolean A00;
    public final int A01 = 5;
    public final long A02;
    public final Runnable A03 = new e2(this);
    public final Deque<L6> A04 = new ArrayDeque();
    public final dQ A05 = new dQ();

    public e1() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.A02 = timeUnit.toNanos(5);
    }
}
