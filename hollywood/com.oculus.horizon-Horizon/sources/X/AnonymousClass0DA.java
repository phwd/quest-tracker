package X;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

/* renamed from: X.0DA  reason: invalid class name */
public class AnonymousClass0DA implements Runnable {
    public static final String __redex_internal_original_name = "bolts.Task$4";
    public final /* synthetic */ AnonymousClass0Cz A00;
    public final /* synthetic */ AnonymousClass0DD A01;
    public final /* synthetic */ Callable A02;

    public AnonymousClass0DA(AnonymousClass0Cz r1, AnonymousClass0DD r2, Callable callable) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = callable;
    }

    public final void run() {
        AnonymousClass0Cz r0 = this.A00;
        if (r0 == null || !r0.A00.A03()) {
            try {
                this.A01.A02(this.A02.call());
            } catch (CancellationException unused) {
                this.A01.A00();
            } catch (Exception e) {
                this.A01.A01(e);
            }
        } else {
            this.A01.A00();
        }
    }
}
