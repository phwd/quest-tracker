package X;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: X.1x8  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12241x8 extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, AbstractC12271xB {
    public static final Object A00 = new Object();
    public static final Object A01 = new Object();
    public static final Object A02 = new Object();
    public static final Object A03 = new Object();
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ScheduledRunnable";
    public static final long serialVersionUID = -6120223772001106981L;
    public final Runnable actual;

    public RunnableC12241x8(Runnable runnable, AbstractC12231x7 r3) {
        super(3);
        this.actual = runnable;
        lazySet(0, r3);
    }

    public final void A00(Future<?> future) {
        Object obj;
        do {
            boolean z = true;
            obj = get(1);
            if (obj != A01) {
                if (obj == A03) {
                    z = false;
                } else if (obj != A00) {
                }
                future.cancel(z);
                return;
            }
            return;
        } while (!compareAndSet(1, obj, future));
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        while (true) {
            Object obj6 = get(1);
            obj = A01;
            if (obj6 == obj || obj6 == (obj4 = A03) || obj6 == (obj5 = A00)) {
                break;
            }
            boolean z = false;
            if (get(2) != Thread.currentThread()) {
                z = true;
                obj4 = obj5;
            }
            if (compareAndSet(1, obj6, obj4)) {
                if (obj6 != null) {
                    ((Future) obj6).cancel(z);
                }
            }
        }
        do {
            obj2 = get(0);
            if (obj2 == obj || obj2 == (obj3 = A02) || obj2 == null) {
                return;
            }
        } while (!compareAndSet(0, obj2, obj3));
        ((AbstractC12231x7) obj2).A2Z(this);
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        run();
        return null;
    }

    public final void run() {
        Object obj;
        Object obj2;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
        } catch (Throwable th) {
            lazySet(2, null);
            Object obj3 = get(0);
            if (!(obj3 == A02 || !compareAndSet(0, obj3, A01) || obj3 == null)) {
                ((AbstractC12231x7) obj3).A2Z(this);
            }
            do {
                obj2 = get(1);
                if (obj2 == A03 || obj2 == A00) {
                    throw th;
                }
            } while (!compareAndSet(1, obj2, A01));
            throw th;
        }
        lazySet(2, null);
        Object obj4 = get(0);
        if (!(obj4 == A02 || !compareAndSet(0, obj4, A01) || obj4 == null)) {
            ((AbstractC12231x7) obj4).A2Z(this);
        }
        do {
            obj = get(1);
            if (obj == A03 || obj == A00) {
                return;
            }
        } while (!compareAndSet(1, obj, A01));
    }
}
