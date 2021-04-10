package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;

/* renamed from: X.1L  reason: invalid class name */
public final class AnonymousClass1L extends AbstractC00141v {
    public ListenableFuture A00;
    public Future A01;

    @Override // X.SH
    public final void afterDone() {
        maybePropagateCancellationTo(this.A00);
        Future future = this.A01;
        if (future != null) {
            future.cancel(false);
        }
        this.A00 = null;
        this.A01 = null;
    }

    @Override // X.SH
    public final String pendingToString() {
        ListenableFuture listenableFuture = this.A00;
        if (listenableFuture == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("inputFuture=[");
        sb.append(listenableFuture);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass1L(ListenableFuture listenableFuture) {
        this.A00 = listenableFuture;
    }
}
