package X;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* renamed from: X.00C  reason: invalid class name */
public final class AnonymousClass00C<V> extends AnonymousClass02i<V> implements RunnableFuture<V> {
    public volatile AnonymousClass122<?> A00;

    @Override // X.AnonymousClass0BR
    public final String pendingToString() {
        AnonymousClass122<?> r2 = this.A00;
        if (r2 == null) {
            return super.pendingToString();
        }
        StringBuilder sb = new StringBuilder("task=[");
        sb.append(r2);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        AnonymousClass122<?> r0 = this.A00;
        if (r0 != null) {
            r0.run();
        }
        this.A00 = null;
    }

    public AnonymousClass00C(Callable<V> callable) {
        this.A00 = new C01460ee(this, callable);
    }

    @Override // X.AnonymousClass0BR
    public final void afterDone() {
        AnonymousClass122<?> r2;
        super.afterDone();
        if (wasInterrupted() && (r2 = this.A00) != null) {
            Runnable runnable = (Runnable) r2.get();
            if ((runnable instanceof Thread) && r2.compareAndSet(runnable, AnonymousClass122.A01)) {
                ((Thread) runnable).interrupt();
                r2.set(AnonymousClass122.A00);
            }
        }
        this.A00 = null;
    }
}
