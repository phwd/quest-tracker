package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicInteger;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IL  reason: invalid class name */
public abstract class AnonymousClass0IL<T> implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.common.executors.StatefulRunnable";
    public final AtomicInteger A00 = new AtomicInteger(0);

    public void A01() {
    }

    public void A03(Exception exc) {
    }

    public void A04(T t) {
    }

    public void A05(T t) {
    }

    public abstract T A06() throws Exception;

    public final void A02() {
        if (this.A00.compareAndSet(0, 2)) {
            A01();
        }
    }

    public final void run() {
        AtomicInteger atomicInteger = this.A00;
        if (atomicInteger.compareAndSet(0, 1)) {
            try {
                T A06 = A06();
                atomicInteger.set(3);
                try {
                    A05(A06);
                } finally {
                    A04(A06);
                }
            } catch (Exception e) {
                atomicInteger.set(4);
                A03(e);
            }
        }
    }
}
