package X;

import android.os.Handler;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.08t  reason: invalid class name and case insensitive filesystem */
public class RunnableC002808t<V> extends AnonymousClass0IS<V> implements Runnable, AbstractScheduledFutureC06380mz<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.ListenableScheduledFutureImpl";
    public final AnonymousClass0n6<V> A00;

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

    @Override // X.AnonymousClass0X9, X.AnonymousClass0n9
    public final /* bridge */ /* synthetic */ Object A00() {
        return this.A00;
    }

    @Override // X.AnonymousClass0n9
    public final /* bridge */ /* synthetic */ Future A01() {
        return this.A00;
    }

    public RunnableC002808t(Handler handler, Runnable runnable, V v) {
        super(handler);
        this.A00 = new AnonymousClass0n6<>(runnable, v);
    }

    public RunnableC002808t(Handler handler, Callable<V> callable) {
        super(handler);
        this.A00 = new AnonymousClass0n6<>(callable);
    }
}
