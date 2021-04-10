package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class fd implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass6f A01;
    public final /* synthetic */ Executor A02;

    public fd(Executor executor, AnonymousClass6f r3) {
        this.A02 = executor;
        this.A01 = r3;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new f8(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
