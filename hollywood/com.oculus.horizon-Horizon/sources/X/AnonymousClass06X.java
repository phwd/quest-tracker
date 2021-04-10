package X;

import com.google.common.util.concurrent.ListenableFuture;

/* renamed from: X.06X  reason: invalid class name */
public abstract class AnonymousClass06X<V> extends AnonymousClass0Bg<V> {
    public final ListenableFuture<V> A00;

    public AnonymousClass06X(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }

    @Override // X.AnonymousClass0Bg
    /* renamed from: A02 */
    public final ListenableFuture<V> A01() {
        return this.A00;
    }
}
