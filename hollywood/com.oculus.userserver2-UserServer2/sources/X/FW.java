package X;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class FW implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$4";
    public final /* synthetic */ FZ A00;
    public final /* synthetic */ Callable A01;

    public FW(FZ fz, Callable callable) {
        this.A00 = fz;
        this.A01 = callable;
    }

    public final void run() {
        try {
            this.A00.A01(this.A01.call());
        } catch (CancellationException unused) {
            if (!this.A00.A00.A02()) {
                throw new IllegalStateException("Cannot cancel a completed task.");
            }
        } catch (Exception e) {
            this.A00.A00(e);
        }
    }
}
