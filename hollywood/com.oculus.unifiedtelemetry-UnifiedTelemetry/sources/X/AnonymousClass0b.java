package X;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
/* renamed from: X.0b  reason: invalid class name */
public final class AnonymousClass0b extends AbstractC00136k<Void> implements Runnable {
    public static final String __redex_internal_original_name = "com.google.common.util.concurrent.MoreExecutors$ScheduledListeningDecorator$NeverSuccessfulListenableFutureTask";
    public final Runnable A00;

    public final void run() {
        try {
            this.A00.run();
        } catch (Throwable th) {
            setException(th);
            if ((th instanceof RuntimeException) || (th instanceof Error)) {
                throw th;
            }
            throw new RuntimeException(th);
        }
    }

    public AnonymousClass0b(Runnable runnable) {
        if (runnable != null) {
            this.A00 = runnable;
            return;
        }
        throw null;
    }
}
