package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class Bi implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ AbstractC00136k A01;
    public final /* synthetic */ Executor A02;

    public Bi(Executor executor, AbstractC00136k r3) {
        this.A02 = executor;
        this.A01 = r3;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new Bh(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
