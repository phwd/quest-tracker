package X;

import com.google.common.util.concurrent.ListenableFuture;

public final class X7<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractFuture$SetFuture";
    public final AnonymousClass6f<V> A00;
    public final ListenableFuture<? extends V> A01;

    public final void run() {
        AnonymousClass6f<V> r2 = this.A00;
        if (r2.value == this) {
            if (AnonymousClass6f.ATOMIC_HELPER.A04(r2, this, AnonymousClass6f.getFutureValue(this.A01))) {
                AnonymousClass6f.complete(r2);
            }
        }
    }

    public X7(AnonymousClass6f<V> r1, ListenableFuture<? extends V> listenableFuture) {
        this.A00 = r1;
        this.A01 = listenableFuture;
    }
}
