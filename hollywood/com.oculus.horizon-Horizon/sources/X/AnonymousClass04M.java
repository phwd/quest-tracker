package X;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@TargetApi(9)
/* renamed from: X.04M  reason: invalid class name */
public class AnonymousClass04M<V> extends RunnableC002708s<V> implements RunnableFuture<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.WakingExecutorService$ListenableScheduledRunnableFuture";
    public final /* synthetic */ AnonymousClass08r A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass04M(AnonymousClass08r r1, Runnable runnable, V v) {
        super(r1, runnable, v);
        this.A00 = r1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass04M(AnonymousClass08r r1, Callable<V> callable) {
        super(r1, callable);
        this.A00 = r1;
    }
}
