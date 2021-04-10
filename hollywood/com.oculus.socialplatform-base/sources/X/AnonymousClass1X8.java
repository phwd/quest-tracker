package X;

import com.facebook.rti.common.guavalite.annotations.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
/* renamed from: X.1X8  reason: invalid class name */
public class AnonymousClass1X8<V> extends AnonymousClass1XL<V> implements AbstractScheduledFutureC02560jD<V>, Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledFutureImpl";
    public final AnonymousClass1XG<V> A00;
    public final /* synthetic */ AnonymousClass1X5 A01;

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
    public AnonymousClass1X8(AnonymousClass1X5 r2, Runnable runnable, V v) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new AnonymousClass1XG<>(runnable, v);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1X8(AnonymousClass1X5 r2, Callable<V> callable) {
        super(r2.A00);
        this.A01 = r2;
        this.A00 = new AnonymousClass1XG<>(callable);
    }
}
