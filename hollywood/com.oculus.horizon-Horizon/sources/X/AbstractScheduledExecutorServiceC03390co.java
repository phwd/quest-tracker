package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Beta
@GwtIncompatible
/* renamed from: X.0co  reason: invalid class name and case insensitive filesystem */
public interface AbstractScheduledExecutorServiceC03390co extends ScheduledExecutorService, AbstractExecutorServiceC08680xK {
    ListenableScheduledFuture<?> A8S(Runnable runnable, long j, TimeUnit timeUnit);
}
