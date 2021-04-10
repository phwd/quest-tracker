package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.Uq  reason: case insensitive filesystem */
public final class RunnableC0380Uq implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractFuture$SetFuture";
    public final SH A00;
    public final ListenableFuture A01;

    public final void run() {
        SH sh = this.A00;
        if (sh.value == this) {
            if (SH.ATOMIC_HELPER.A04(sh, this, SH.getFutureValue(this.A01))) {
                SH.complete(sh);
            }
        }
    }

    public RunnableC0380Uq(SH sh, ListenableFuture listenableFuture) {
        this.A00 = sh;
        this.A01 = listenableFuture;
    }
}
