package X;

import java.util.concurrent.CancellationException;

/* renamed from: X.0Hp  reason: invalid class name and case insensitive filesystem */
public class RunnableC01480Hp implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$14";
    public final /* synthetic */ AnonymousClass0Hn A00;
    public final /* synthetic */ AnonymousClass0Hv A01;
    public final /* synthetic */ C01520Hw A02;

    public RunnableC01480Hp(C01520Hw r1, AnonymousClass0Hn r2, AnonymousClass0Hv r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    public final void run() {
        try {
            this.A02.A02(this.A00.A8Z(this.A01));
        } catch (CancellationException unused) {
            this.A02.A00();
        } catch (Exception e) {
            this.A02.A01(e);
        }
    }
}
