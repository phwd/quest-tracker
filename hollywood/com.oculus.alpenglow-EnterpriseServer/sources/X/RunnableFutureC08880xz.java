package X;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@TargetApi(9)
/* renamed from: X.0xz  reason: invalid class name and case insensitive filesystem */
public class RunnableFutureC08880xz<V> extends RunnableC08760xm<V> implements RunnableFuture<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledRunnableFuture";
    public final /* synthetic */ ExecutorServiceC08580xU A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableFutureC08880xz(ExecutorServiceC08580xU r1, Runnable runnable, V v) {
        super(r1, runnable, v);
        this.A00 = r1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RunnableFutureC08880xz(ExecutorServiceC08580xU r1, Callable<V> callable) {
        super(r1, callable);
        this.A00 = r1;
    }
}
