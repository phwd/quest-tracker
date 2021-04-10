package X;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class D9 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$4";
    public final /* synthetic */ DC A00;
    public final /* synthetic */ Callable A01;

    public D9(DC dc, Callable callable) {
        this.A00 = dc;
        this.A01 = callable;
    }

    public final void run() {
        try {
            this.A00.A02(this.A01.call());
        } catch (CancellationException unused) {
            this.A00.A00();
        } catch (Exception e) {
            this.A00.A01(e);
        }
    }
}
