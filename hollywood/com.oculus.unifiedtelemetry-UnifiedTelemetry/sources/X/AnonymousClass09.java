package X;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* renamed from: X.09  reason: invalid class name */
public final class AnonymousClass09<V> extends AbstractC00030p<V> implements RunnableFuture<V> {
    public volatile Bd<?> A00;

    @Override // X.AbstractC00136k
    public final String pendingToString() {
        Bd<?> bd = this.A00;
        if (bd == null) {
            return super.pendingToString();
        }
        StringBuilder sb = new StringBuilder("task=[");
        sb.append(bd);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        Bd<?> bd = this.A00;
        if (bd != null) {
            bd.run();
        }
        this.A00 = null;
    }

    public AnonymousClass09(Callable<V> callable) {
        this.A00 = new UD(this, callable);
    }

    @Override // X.AbstractC00136k
    public final void afterDone() {
        Bd<?> bd;
        super.afterDone();
        if (wasInterrupted() && (bd = this.A00) != null) {
            Runnable runnable = (Runnable) bd.get();
            if ((runnable instanceof Thread) && bd.compareAndSet(runnable, Bd.A01)) {
                ((Thread) runnable).interrupt();
                bd.set(Bd.A00);
            }
        }
        this.A00 = null;
    }
}
