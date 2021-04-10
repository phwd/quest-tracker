package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.0wJ  reason: invalid class name and case insensitive filesystem */
public final class RunnableC08290wJ<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractFuture$SetFuture";
    public final AnonymousClass06Z<V> A00;
    public final ListenableFuture<? extends V> A01;

    public final void run() {
        AnonymousClass06Z<V> r2 = this.A00;
        if (r2.value == this) {
            if (AnonymousClass06Z.ATOMIC_HELPER.A04(r2, this, AnonymousClass06Z.getFutureValue(this.A01))) {
                AnonymousClass06Z.complete(r2);
            }
        }
    }

    public RunnableC08290wJ(AnonymousClass06Z<V> r1, ListenableFuture<? extends V> listenableFuture) {
        this.A00 = r1;
        this.A01 = listenableFuture;
    }
}
