package X;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@TargetApi(9)
/* renamed from: X.1X7  reason: invalid class name */
public class AnonymousClass1X7<V> extends AnonymousClass1X8<V> implements RunnableFuture<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledRunnableFuture";
    public final /* synthetic */ AnonymousClass1X5 A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1X7(AnonymousClass1X5 r1, Runnable runnable, V v) {
        super(r1, runnable, v);
        this.A00 = r1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1X7(AnonymousClass1X5 r1, Callable<V> callable) {
        super(r1, callable);
        this.A00 = r1;
    }
}
