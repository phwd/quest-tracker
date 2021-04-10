package X;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class V2 implements Executor {
    public boolean A00 = true;
    public final /* synthetic */ SH A01;
    public final /* synthetic */ Executor A02;

    public V2(Executor executor, SH sh) {
        this.A02 = executor;
        this.A01 = sh;
    }

    public final void execute(Runnable runnable) {
        try {
            this.A02.execute(new V1(this, runnable));
        } catch (RejectedExecutionException e) {
            if (this.A00) {
                this.A01.setException(e);
            }
        }
    }
}
