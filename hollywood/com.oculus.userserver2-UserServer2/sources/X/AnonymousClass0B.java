package X;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

@GwtCompatible
/* renamed from: X.0B  reason: invalid class name */
public final class AnonymousClass0B<V> extends AbstractC00040n<V> implements RunnableFuture<V> {
    public volatile AbstractRunnableC0203ey<?> A00;

    @Override // X.AnonymousClass6f
    public final String pendingToString() {
        AbstractRunnableC0203ey<?> eyVar = this.A00;
        if (eyVar == null) {
            return super.pendingToString();
        }
        StringBuilder sb = new StringBuilder("task=[");
        sb.append(eyVar);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        AbstractRunnableC0203ey<?> eyVar = this.A00;
        if (eyVar != null) {
            eyVar.run();
        }
        this.A00 = null;
    }

    public AnonymousClass0B(Callable<V> callable) {
        this.A00 = new ME(this, callable);
    }

    @Override // X.AnonymousClass6f
    public final void afterDone() {
        AbstractRunnableC0203ey<?> eyVar;
        super.afterDone();
        if (wasInterrupted() && (eyVar = this.A00) != null) {
            Runnable runnable = (Runnable) eyVar.get();
            if ((runnable instanceof Thread) && eyVar.compareAndSet(runnable, AbstractRunnableC0203ey.A01)) {
                ((Thread) runnable).interrupt();
                eyVar.set(AbstractRunnableC0203ey.A00);
            }
        }
        this.A00 = null;
    }
}
