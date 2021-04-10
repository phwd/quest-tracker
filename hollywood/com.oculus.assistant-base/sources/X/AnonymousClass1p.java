package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1p  reason: invalid class name */
public final class AnonymousClass1p extends SE implements ListenableFuture, ScheduledFuture {
    public final ScheduledFuture A00;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.A00.compareTo(obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.A00.getDelay(timeUnit);
    }

    public AnonymousClass1p(ListenableFuture listenableFuture, ScheduledFuture scheduledFuture) {
        super(listenableFuture);
        this.A00 = scheduledFuture;
    }

    @Override // X.AbstractFutureC1191us
    public final boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            this.A00.cancel(z);
        }
        return cancel;
    }
}
