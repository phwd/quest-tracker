package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xN  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12381xN extends AtomicReference<Runnable> implements Runnable, AbstractC12271xB, AbstractC12181wx {
    public static final String __redex_internal_original_name = "io.reactivex.internal.schedulers.ExecutorScheduler$DelayedRunnable";
    public static final long serialVersionUID = -4101336210206799084L;
    public final AnonymousClass1xW direct = new AnonymousClass1xW();
    public final AnonymousClass1xW timed = new AnonymousClass1xW();

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (getAndSet(null) != null) {
            this.timed.dispose();
            this.direct.dispose();
        }
    }

    public RunnableC12381xN(Runnable runnable) {
        super(runnable);
    }

    public final void run() {
        Runnable runnable = (Runnable) get();
        if (runnable != null) {
            try {
                runnable.run();
            } finally {
                lazySet(null);
                AnonymousClass1xW r0 = this.timed;
                EnumC12511xi r1 = EnumC12511xi.DISPOSED;
                r0.lazySet(r1);
                this.direct.lazySet(r1);
            }
        }
    }
}
