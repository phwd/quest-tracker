package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
/* renamed from: X.0A  reason: invalid class name */
public final class AnonymousClass0A<V> extends AbstractC00030p<V> {
    @NullableDecl
    public ListenableFuture<V> A00;
    @NullableDecl
    public Future<?> A01;

    @Override // X.AbstractC00136k
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        Future<?> future = this.A01;
        if (future != null) {
            future.cancel(false);
        }
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.AbstractC00136k
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

    public AnonymousClass0A(ListenableFuture<V> listenableFuture) {
        this.A00 = listenableFuture;
    }
}
