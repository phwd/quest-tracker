package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1X9  reason: invalid class name */
public class AnonymousClass1X9<V> extends AnonymousClass1XL<V> implements Runnable, AbstractScheduledFutureC02560jD<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.ListenableScheduledFutureImpl";
    public final AnonymousClass1XG<V> A00;

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

    public AnonymousClass1X9(Handler handler, Runnable runnable, V v) {
        super(handler);
        this.A00 = new AnonymousClass1XG<>(runnable, v);
    }

    public AnonymousClass1X9(Handler handler, Callable<V> callable) {
        super(handler);
        this.A00 = new AnonymousClass1XG<>(callable);
    }
}
