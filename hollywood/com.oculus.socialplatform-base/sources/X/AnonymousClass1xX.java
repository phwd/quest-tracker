package X;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.1xX  reason: invalid class name */
public final class AnonymousClass1xX extends AtomicBoolean implements AbstractC12271xB, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ExecutorScheduler$ExecutorWorker$BooleanRunnable";
    public static final long serialVersionUID = -2421395018820541164L;
    public final Runnable actual;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        lazySet(true);
    }

    public AnonymousClass1xX(Runnable runnable) {
        this.actual = runnable;
    }

    public final void run() {
        if (!get()) {
            try {
                this.actual.run();
            } finally {
                lazySet(true);
            }
        }
    }
}
