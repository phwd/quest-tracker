package X;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/* renamed from: X.1x6  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12221x6 implements AbstractC12271xB, Runnable, AbstractC12181wx {
    public static final String __redex_internal_original_name = "io.reactivex.Scheduler$DisposeTask";
    @Nullable
    public Thread A00;
    @NonNull
    public final AbstractC12411xQ A01;
    @NonNull
    public final Runnable A02;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (this.A00 == Thread.currentThread()) {
            AbstractC12411xQ r1 = this.A01;
            if (r1 instanceof AnonymousClass1x2) {
                AnonymousClass1x2 r12 = (AnonymousClass1x2) r1;
                if (!r12.A01) {
                    r12.A01 = true;
                    r12.A00.shutdown();
                    return;
                }
                return;
            }
        }
        this.A01.dispose();
    }

    public RunnableC12221x6(@NonNull Runnable runnable, @NonNull AbstractC12411xQ r2) {
        this.A02 = runnable;
        this.A01 = r2;
    }

    public final void run() {
        this.A00 = Thread.currentThread();
        try {
            this.A02.run();
        } finally {
            dispose();
            this.A00 = null;
        }
    }
}
