package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.0BP  reason: invalid class name */
public abstract class AnonymousClass0BP<V> extends AnonymousClass0M0<V> {
    public final ListenableFuture<V> A00;

    public AnonymousClass0BP(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }

    @Override // X.AnonymousClass0M0
    /* renamed from: A02 */
    public final ListenableFuture<V> A01() {
        return this.A00;
    }
}
