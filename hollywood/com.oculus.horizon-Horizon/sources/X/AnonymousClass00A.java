package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
/* renamed from: X.00A  reason: invalid class name */
public final class AnonymousClass00A<V> extends AbstractC000300p<V> {
    @NullableDecl
    public ListenableFuture<V> A00;
    @NullableDecl
    public Future<?> A01;

    @Override // X.AnonymousClass06Z
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        Future<?> future = this.A01;
        if (future != null) {
            future.cancel(false);
        }
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.AnonymousClass06Z
    public final String pendingToString() {
        ListenableFuture<V> listenableFuture = this.A00;
        if (listenableFuture == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("inputFuture=[");
        sb.append(listenableFuture);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass00A(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }
}
