package X;

import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
/* renamed from: X.0xm  reason: invalid class name and case insensitive filesystem */
public class RunnableC08760xm<V> extends AbstractC08940y9<V> implements AbstractScheduledFutureC04840ho<V>, Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledFutureImpl";
    public final C08930y8<V> A00;
    public final /* synthetic */ ExecutorServiceC08580xU A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void run() {
        this.A00.run();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableC08760xm(ExecutorServiceC08580xU r2, Runnable runnable, V v) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new C08930y8<>(runnable, v);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableC08760xm(ExecutorServiceC08580xU r2, Callable<V> callable) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new C08930y8<>(callable);
    }
}
