package X;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0W  reason: invalid class name */
public final class AnonymousClass0W<V> extends AnonymousClass6d<V> implements ListenableScheduledFuture<V>, ScheduledFuture {
    public final ScheduledFuture<?> A00;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.A00.compareTo(obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.A00.getDelay(timeUnit);
    }

    public AnonymousClass0W(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
        super(listenableFuture);
        this.A00 = scheduledFuture;
    }

    @Override // X.MJ
    public final boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            this.A00.cancel(z);
        }
        return cancel;
    }
}
