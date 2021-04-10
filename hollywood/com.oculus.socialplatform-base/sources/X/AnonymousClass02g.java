package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Beta
@GwtCompatible
/* renamed from: X.02g  reason: invalid class name */
public final class AnonymousClass02g<V> extends AnonymousClass0BP<V> implements ListenableScheduledFuture<V>, ScheduledFuture {
    public final ScheduledFuture<?> A00;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.A00.compareTo(obj);
    }

    public final long getDelay(TimeUnit timeUnit) {
        return this.A00.getDelay(timeUnit);
    }

    public AnonymousClass02g(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
        super(listenableFuture);
        this.A00 = scheduledFuture;
    }

    @Override // X.AbstractFutureC01490ei
    public final boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            this.A00.cancel(z);
        }
        return cancel;
    }
}
