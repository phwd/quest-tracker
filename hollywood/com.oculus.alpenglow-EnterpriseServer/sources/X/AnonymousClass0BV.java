package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.0BV  reason: invalid class name */
public abstract class AnonymousClass0BV<V> extends AnonymousClass0IM<V> {
    public final ListenableFuture<V> A00;

    @Override // X.AnonymousClass0IM
    /* renamed from: A02 */
    public final ListenableFuture<V> A01() {
        return this.A00;
    }

    public AnonymousClass0BV(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }
}
