package X;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0wh  reason: invalid class name and case insensitive filesystem */
public final class C08510wh {
    public static final Executor A06 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new AnonymousClass0w6("OkHttp ConnectionPool", true));
    public boolean A00;
    public final int A01 = 5;
    public final long A02;
    public final Runnable A03 = new RunnableC08520wi(this);
    public final Deque<C01250Mm> A04 = new ArrayDeque();
    public final C08130vw A05 = new C08130vw();

    public C08510wh() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.A02 = timeUnit.toNanos(5);
    }
}
