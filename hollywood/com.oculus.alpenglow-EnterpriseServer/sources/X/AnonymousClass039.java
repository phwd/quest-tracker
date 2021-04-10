package X;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* renamed from: X.039  reason: invalid class name */
public final class AnonymousClass039<V> extends AnonymousClass063<V> implements RunnableFuture<V> {
    public volatile AbstractRunnableC00890Bm<?> A00;

    @Override // X.AnonymousClass0BX
    public final String pendingToString() {
        AbstractRunnableC00890Bm<?> r2 = this.A00;
        if (r2 == null) {
            return super.pendingToString();
        }
        return "task=[" + r2 + "]";
    }

    public final void run() {
        AbstractRunnableC00890Bm<?> r0 = this.A00;
        if (r0 != null) {
            r0.run();
        }
        this.A00 = null;
    }

    public AnonymousClass039(Callable<V> callable) {
        this.A00 = new C02200Xd(this, callable);
    }

    @Override // X.AnonymousClass0BX
    public final void afterDone() {
        AbstractRunnableC00890Bm<?> r2;
        super.afterDone();
        if (wasInterrupted() && (r2 = this.A00) != null) {
            Runnable runnable = (Runnable) r2.get();
            if ((runnable instanceof Thread) && r2.compareAndSet(runnable, AbstractRunnableC00890Bm.A01)) {
                ((Thread) runnable).interrupt();
                r2.set(AbstractRunnableC00890Bm.A00);
            }
        }
        this.A00 = null;
    }
}
