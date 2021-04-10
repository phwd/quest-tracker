package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xn  reason: invalid class name and case insensitive filesystem */
public class RunnableC08770xn<V> extends AbstractC08940y9<V> implements Runnable, AbstractScheduledFutureC04840ho<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.ListenableScheduledFutureImpl";
    public final C08930y8<V> A00;

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

    public RunnableC08770xn(Handler handler, Runnable runnable, V v) {
        super(handler);
        this.A00 = new C08930y8<>(runnable, v);
    }

    public RunnableC08770xn(Handler handler, Callable<V> callable) {
        super(handler);
        this.A00 = new C08930y8<>(callable);
    }
}
