package X;

import java.util.concurrent.CancellationException;

/* renamed from: X.0D7  reason: invalid class name */
public class AnonymousClass0D7 implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$15";
    public final /* synthetic */ AnonymousClass0D4 A00;
    public final /* synthetic */ AnonymousClass0DC A01;
    public final /* synthetic */ AnonymousClass0DD A02;

    public AnonymousClass0D7(AnonymousClass0DD r1, AnonymousClass0D4 r2, AnonymousClass0DC r3) {
        this.A02 = r1;
        this.A00 = r2;
        this.A01 = r3;
    }

    public final void run() {
        try {
            AnonymousClass0DC r1 = (AnonymousClass0DC) this.A00.then(this.A01);
            if (r1 == null) {
                this.A02.A02(null);
            } else {
                r1.A09(new AnonymousClass0r7(this));
            }
        } catch (CancellationException unused) {
            this.A02.A00();
        } catch (Exception e) {
            this.A02.A01(e);
        }
    }
}
