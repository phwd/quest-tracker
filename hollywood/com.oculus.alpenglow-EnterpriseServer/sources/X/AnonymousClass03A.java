package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
/* renamed from: X.03A  reason: invalid class name */
public final class AnonymousClass03A<V> extends AnonymousClass063<V> {
    @NullableDecl
    public ListenableFuture<V> A00;
    @NullableDecl
    public Future<?> A01;

    @Override // X.AnonymousClass0BX
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        Future<?> future = this.A01;
        if (future != null) {
            future.cancel(false);
        }
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.AnonymousClass0BX
    public final String pendingToString() {
        ListenableFuture<V> listenableFuture = this.A00;
        if (listenableFuture == null) {
            return null;
        }
        return "inputFuture=[" + listenableFuture + "]";
    }

    public AnonymousClass03A(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }
}
