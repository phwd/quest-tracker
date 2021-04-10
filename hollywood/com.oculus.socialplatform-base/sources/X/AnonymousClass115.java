package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.115  reason: invalid class name */
public final class AnonymousClass115<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractFuture$SetFuture";
    public final AnonymousClass0BR<V> A00;
    public final ListenableFuture<? extends V> A01;

    public final void run() {
        AnonymousClass0BR<V> r2 = this.A00;
        if (r2.value == this) {
            if (AnonymousClass0BR.ATOMIC_HELPER.A04(r2, this, AnonymousClass0BR.getFutureValue(this.A01))) {
                AnonymousClass0BR.complete(r2);
            }
        }
    }

    public AnonymousClass115(AnonymousClass0BR<V> r1, ListenableFuture<? extends V> listenableFuture) {
        this.A00 = r1;
        this.A01 = listenableFuture;
    }
}
