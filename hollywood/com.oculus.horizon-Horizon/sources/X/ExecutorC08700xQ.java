package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.0xQ  reason: invalid class name and case insensitive filesystem */
public class ExecutorC08700xQ implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass06Z A01;
    public final /* synthetic */ Executor A02;

    public ExecutorC08700xQ(Executor executor, AnonymousClass06Z r3) {
        this.A02 = executor;
        this.A01 = r3;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new RunnableC08690xP(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
