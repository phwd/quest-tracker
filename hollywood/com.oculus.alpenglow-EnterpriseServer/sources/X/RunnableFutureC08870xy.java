package X;

import android.annotation.TargetApi;
import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@TargetApi(9)
/* renamed from: X.0xy  reason: invalid class name and case insensitive filesystem */
public class RunnableFutureC08870xy<V> extends RunnableC08770xn<V> implements RunnableFuture<V> {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.common.executors.HandlerExecutorServiceImpl$ListenableScheduledRunnableFuture";

    public RunnableFutureC08870xy(Handler handler, Runnable runnable, V v) {
        super(handler, runnable, v);
    }

    public RunnableFutureC08870xy(Handler handler, Callable<V> callable) {
        super(handler, callable);
    }
}
