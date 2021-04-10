package X;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
@ReflectionSupport(ReflectionSupport.Level.FULL)
/* renamed from: X.0Bm  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractRunnableC00890Bm<T> extends AtomicReference<Runnable> implements Runnable {
    public static final Runnable A00 = new AnonymousClass0C2();
    public static final Runnable A01 = new AnonymousClass0C2();
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.InterruptibleTask";

    public abstract T A00() throws Exception;

    public abstract String A01();

    public abstract void A02(@NullableDecl T t, @NullableDecl Throwable th);

    public abstract boolean A03();

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: X.0Bm<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet(null, currentThread)) {
            boolean z = !A03();
            if (z) {
                try {
                    obj = A00();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, A00)) {
                        while (get() == A01) {
                            Thread.yield();
                        }
                    }
                    A02(null, th);
                    return;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, A00)) {
                while (get() == A01) {
                    Thread.yield();
                }
            }
            if (z) {
                A02(obj, null);
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
            str = AnonymousClass006.A07("running=[RUNNING ON ", ((Thread) runnable).getName(), "]");
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return AnonymousClass006.A07(str, ", ", A01());
    }
}
