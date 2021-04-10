package X;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import com.oculus.common.build.BuildConfig;
import java.util.concurrent.atomic.AtomicReference;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
@ReflectionSupport(ReflectionSupport.Level.FULL)
/* renamed from: X.ey  reason: case insensitive filesystem */
public abstract class AbstractRunnableC0203ey<T> extends AtomicReference<Runnable> implements Runnable {
    public static final Runnable A00 = new RunnableC0202ex();
    public static final Runnable A01 = new RunnableC0202ex();
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.InterruptibleTask";

    public final void run() {
        Thread currentThread = Thread.currentThread();
        V v = null;
        if (compareAndSet(null, currentThread)) {
            ME me = (ME) this;
            boolean z = !me.this$0.isDone();
            if (z) {
                try {
                    v = me.callable.call();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, A00)) {
                        while (get() == A01) {
                            Thread.yield();
                        }
                    }
                    me.this$0.setException(th);
                    return;
                }
            }
            if (!compareAndSet(currentThread, A00)) {
                while (get() == A01) {
                    Thread.yield();
                }
            }
            if (z) {
                me.this$0.set(v);
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == A00) {
            str = "running=[DONE]";
        } else if (runnable == A01) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = AnonymousClass06.A04("running=[RUNNING ON ", ((Thread) runnable).getName(), "]");
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return AnonymousClass06.A04(str, ", ", ((ME) this).callable.toString());
    }
}
