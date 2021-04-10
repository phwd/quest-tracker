package X;

import io.reactivex.annotations.NonNull;

/* renamed from: X.1xD  reason: invalid class name and case insensitive filesystem */
public final class RunnableC12291xD implements AbstractC12271xB, Runnable, AbstractC12181wx {
    public static final String __redex_internal_original_name = "io.reactivex.Scheduler$PeriodicDirectTask";
    @NonNull
    public final AbstractC12411xQ A00;
    @NonNull
    public final Runnable A01;
    public volatile boolean A02;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A02 = true;
        this.A00.dispose();
    }

    public final void run() {
        if (!this.A02) {
            try {
                this.A01.run();
            } catch (Throwable th) {
                C12261xA.A00(th);
                this.A00.dispose();
                throw C12301xE.A00(th);
            }
        }
    }

    public RunnableC12291xD(@NonNull Runnable runnable, @NonNull AbstractC12411xQ r2) {
        this.A01 = runnable;
        this.A00 = r2;
    }
}
