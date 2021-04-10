package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: X.12F  reason: invalid class name */
public class AnonymousClass12F implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass0BR A01;
    public final /* synthetic */ Executor A02;

    public AnonymousClass12F(Executor executor, AnonymousClass0BR r3) {
        this.A02 = executor;
        this.A01 = r3;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new AnonymousClass12E(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
