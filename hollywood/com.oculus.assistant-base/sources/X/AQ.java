package X;

import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class AQ extends ThreadPoolExecutor {
    public final /* synthetic */ AR A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AQ(AR ar, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
        this.A00 = ar;
    }

    public final void afterExecute(Runnable runnable, Throwable th) {
        C0514bB.A02(runnable, "runnable");
        if (th == null) {
            if (runnable instanceof Future) {
                try {
                    ((Future) runnable).get();
                    return;
                } catch (CancellationException e) {
                    if (C0139Dd.A01.A3Y(2)) {
                        C0139Dd.A01.A5M("CrashingExecutorPolicy", "Runnable is canceled", e);
                        return;
                    }
                    return;
                } catch (ExecutionException e2) {
                    th = e2.getCause();
                    if (th == null) {
                        return;
                    }
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            } else {
                return;
            }
        }
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == null) {
            C0139Dd.A0S("CrashingExecutorPolicy", th, "Received otherwise fatal exception, but no uncaught exception handler to fire!");
        } else {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
    }
}
