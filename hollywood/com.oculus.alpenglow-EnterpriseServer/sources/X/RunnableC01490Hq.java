package X;

import java.util.concurrent.CancellationException;

/* renamed from: X.0Hq  reason: invalid class name and case insensitive filesystem */
public class RunnableC01490Hq implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$15";
    public final /* synthetic */ AnonymousClass0Hn A00;
    public final /* synthetic */ AnonymousClass0Hv A01;
    public final /* synthetic */ C01520Hw A02;

    public RunnableC01490Hq(C01520Hw r1, AnonymousClass0Hn r2, AnonymousClass0Hv r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    public final void run() {
        try {
            AnonymousClass0Hv r1 = (AnonymousClass0Hv) this.A00.A8Z(this.A01);
            if (r1 == null) {
                this.A02.A02(null);
            } else {
                r1.A04(new C03290by(this));
            }
        } catch (CancellationException unused) {
            this.A02.A00();
        } catch (Exception e) {
            this.A02.A01(e);
        }
    }
}
