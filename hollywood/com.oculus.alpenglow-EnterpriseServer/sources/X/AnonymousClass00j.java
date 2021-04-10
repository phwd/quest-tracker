package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.00j  reason: invalid class name */
public final class AnonymousClass00j<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TimeoutFuture$Fire";
    @NullableDecl
    public AnonymousClass03A<V> A00;

    public final void run() {
        ListenableFuture<V> listenableFuture;
        AnonymousClass03A<V> r4 = this.A00;
        if (r4 != null && (listenableFuture = r4.A00) != null) {
            this.A00 = null;
            if (listenableFuture.isDone()) {
                r4.setFuture(listenableFuture);
                return;
            }
            try {
                r4.setException(new TimeoutException("Future timed out: " + listenableFuture));
            } finally {
                listenableFuture.cancel(true);
            }
        }
    }

    public AnonymousClass00j(AnonymousClass03A<V> r1) {
        this.A00 = r1;
    }
}
