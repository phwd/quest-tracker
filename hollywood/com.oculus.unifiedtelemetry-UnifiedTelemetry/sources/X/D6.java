package X;

import java.util.concurrent.CancellationException;

public class D6 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$15";
    public final /* synthetic */ D3 A00;
    public final /* synthetic */ DB A01;
    public final /* synthetic */ DC A02;

    public D6(DC dc, D3 d3, DB db) {
        this.A02 = dc;
        this.A00 = d3;
        this.A01 = db;
    }

    public final void run() {
        try {
            DB db = (DB) this.A00.A5U(this.A01);
            if (db == null) {
                this.A02.A02(null);
            } else {
                db.A04(new C0276Zb(this));
            }
        } catch (CancellationException unused) {
            this.A02.A00();
        } catch (Exception e) {
            this.A02.A01(e);
        }
    }
}
