package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Beta
@GwtIncompatible
/* renamed from: X.0ef  reason: invalid class name and case insensitive filesystem */
public interface AbstractScheduledExecutorServiceC01470ef extends ScheduledExecutorService, AnonymousClass129 {
    ListenableScheduledFuture<?> A9V(Runnable runnable, long j, TimeUnit timeUnit);
}
