package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class CT<V> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.TimeoutFuture$Fire";
    @NullableDecl
    public AnonymousClass0A<V> A00;

    public final void run() {
        ListenableFuture<V> listenableFuture;
        AnonymousClass0A<V> r4 = this.A00;
        if (r4 != null && (listenableFuture = r4.A00) != null) {
            this.A00 = null;
            if (listenableFuture.isDone()) {
                r4.setFuture(listenableFuture);
                return;
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Future timed out: ");
                sb.append(listenableFuture);
                r4.setException(new TimeoutException(sb.toString()));
            } finally {
                listenableFuture.cancel(true);
            }
        }
    }

    public CT(AnonymousClass0A<V> r1) {
        this.A00 = r1;
    }
}
