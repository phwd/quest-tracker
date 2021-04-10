package X;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* renamed from: X.009  reason: invalid class name */
public final class AnonymousClass009<V> extends AbstractC000300p<V> implements RunnableFuture<V> {
    public volatile AbstractRunnableC08660xD<?> A00;

    @Override // X.AnonymousClass06Z
    public final String pendingToString() {
        AbstractRunnableC08660xD<?> r2 = this.A00;
        if (r2 == null) {
            return super.pendingToString();
        }
        StringBuilder sb = new StringBuilder("task=[");
        sb.append(r2);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        AbstractRunnableC08660xD<?> r0 = this.A00;
        if (r0 != null) {
            r0.run();
        }
        this.A00 = null;
    }

    public AnonymousClass009(Callable<V> callable) {
        this.A00 = new C03380cn(this, callable);
    }

    @Override // X.AnonymousClass06Z
    public final void afterDone() {
        AbstractRunnableC08660xD<?> r2;
        super.afterDone();
        if (wasInterrupted() && (r2 = this.A00) != null) {
            Runnable runnable = (Runnable) r2.get();
            if ((runnable instanceof Thread) && r2.compareAndSet(runnable, AbstractRunnableC08660xD.A01)) {
                ((Thread) runnable).interrupt();
                r2.set(AbstractRunnableC08660xD.A00);
            }
        }
        this.A00 = null;
    }
}
