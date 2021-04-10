package X;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/* renamed from: X.0Ht  reason: invalid class name and case insensitive filesystem */
public class RunnableC01500Ht implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$4";
    public final /* synthetic */ C01520Hw A00;
    public final /* synthetic */ Callable A01;

    public RunnableC01500Ht(C01520Hw r1, Callable callable) {
        this.A00 = r1;
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
