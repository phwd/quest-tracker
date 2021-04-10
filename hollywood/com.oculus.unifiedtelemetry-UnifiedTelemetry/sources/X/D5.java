package X;

import java.util.concurrent.CancellationException;

public class D5 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$14";
    public final /* synthetic */ D3 A00;
    public final /* synthetic */ DB A01;
    public final /* synthetic */ DC A02;

    public D5(DC dc, D3 d3, DB db) {
        this.A02 = dc;
        this.A00 = d3;
        this.A01 = db;
    }

    public final void run() {
        try {
            this.A02.A02(this.A00.A5U(this.A01));
        } catch (CancellationException unused) {
            this.A02.A00();
        } catch (Exception e) {
            this.A02.A01(e);
        }
    }
}
