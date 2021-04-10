package X;

import android.view.View;

/* renamed from: X.03r  reason: invalid class name and case insensitive filesystem */
public class RunnableC002003r implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionMenuPresenter$OpenOverflowRunnable";
    public AnonymousClass0Mo A00;
    public final /* synthetic */ AnonymousClass0Mm A01;

    public RunnableC002003r(AnonymousClass0Mm r1, AnonymousClass0Mo r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass03V r0;
        AnonymousClass0Mm r3 = this.A01;
        C04280eZ r1 = ((AbstractC04310ee) r3).A03;
        if (!(r1 == null || (r0 = r1.A03) == null)) {
            r0.A6E(r1);
        }
        View view = (View) ((AbstractC04310ee) r3).A05;
        if (!(view == null || view.getWindowToken() == null)) {
            AnonymousClass0Mo r2 = this.A00;
            if (!r2.A05()) {
                if (r2.A01 != null) {
                    C04210eR.A00(r2, 0, 0, false, false);
                }
            }
            r3.A00 = this.A00;
        }
        r3.A04 = null;
    }
}
