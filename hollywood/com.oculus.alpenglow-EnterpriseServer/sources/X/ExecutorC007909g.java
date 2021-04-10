package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.09g  reason: invalid class name and case insensitive filesystem */
public class ExecutorC007909g implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass0BX A01;
    public final /* synthetic */ Executor A02;

    public ExecutorC007909g(Executor executor, AnonymousClass0BX r3) {
        this.A02 = executor;
        this.A01 = r3;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new AnonymousClass09k(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
