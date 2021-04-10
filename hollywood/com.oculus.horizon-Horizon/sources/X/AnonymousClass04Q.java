package X;

import android.annotation.TargetApi;
import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@TargetApi(9)
/* renamed from: X.04Q  reason: invalid class name */
public class AnonymousClass04Q<V> extends RunnableC002808t<V> implements RunnableFuture<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.HandlerExecutorServiceImpl$ListenableScheduledRunnableFuture";

    public AnonymousClass04Q(Handler handler, Runnable runnable, V v) {
        super(handler, runnable, v);
    }

    public AnonymousClass04Q(Handler handler, Callable<V> callable) {
        super(handler, callable);
    }
}
