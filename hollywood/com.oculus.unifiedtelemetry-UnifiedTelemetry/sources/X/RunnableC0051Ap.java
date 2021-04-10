package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.Ap  reason: case insensitive filesystem */
public final class RunnableC0051Ap<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.AbstractFuture$SetFuture";
    public final AbstractC00136k<V> A00;
    public final ListenableFuture<? extends V> A01;

    public final void run() {
        AbstractC00136k<V> r2 = this.A00;
        if (r2.value == this) {
            if (AbstractC00136k.ATOMIC_HELPER.A04(r2, this, AbstractC00136k.getFutureValue(this.A01))) {
                AbstractC00136k.complete(r2);
            }
        }
    }

    public RunnableC0051Ap(AbstractC00136k<V> r1, ListenableFuture<? extends V> listenableFuture) {
        this.A00 = r1;
        this.A01 = listenableFuture;
    }
}
