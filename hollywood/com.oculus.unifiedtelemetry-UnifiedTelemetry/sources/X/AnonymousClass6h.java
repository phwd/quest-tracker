package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.6h  reason: invalid class name */
public abstract class AnonymousClass6h<V> extends AbstractC0059Ax<V> {
    public final ListenableFuture<V> A00;

    public AnonymousClass6h(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }

    @Override // X.AbstractC0059Ax
    /* renamed from: A02 */
    public final ListenableFuture<V> A01() {
        return this.A00;
    }
}
