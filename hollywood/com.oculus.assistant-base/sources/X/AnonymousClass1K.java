package X;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* renamed from: X.1K  reason: invalid class name */
public final class AnonymousClass1K extends AbstractC00141v implements RunnableFuture {
    public volatile AbstractRunnableC0386Uy A00;

    @Override // X.SH
    public final String pendingToString() {
        AbstractRunnableC0386Uy uy = this.A00;
        if (uy == null) {
            return super.pendingToString();
        }
        StringBuilder sb = new StringBuilder("task=[");
        sb.append(uy);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        AbstractRunnableC0386Uy uy = this.A00;
        if (uy != null) {
            uy.run();
        }
        this.A00 = null;
    }

    public AnonymousClass1K(Callable callable) {
        this.A00 = new C1194uv(this, callable);
    }

    @Override // X.SH
    public final void afterDone() {
        AbstractRunnableC0386Uy uy;
        super.afterDone();
        if (wasInterrupted() && (uy = this.A00) != null) {
            Runnable runnable = (Runnable) uy.get();
            if ((runnable instanceof Thread) && uy.compareAndSet(runnable, AbstractRunnableC0386Uy.A01)) {
                ((Thread) runnable).interrupt();
                uy.set(AbstractRunnableC0386Uy.A00);
            }
        }
        this.A00 = null;
    }
}
