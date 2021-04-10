package X;

import android.view.View;

/* renamed from: X.1su  reason: invalid class name and case insensitive filesystem */
public class RunnableC11831su implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionMenuPresenter$OpenOverflowRunnable";
    public AnonymousClass1t5 A00;
    public final /* synthetic */ C11591sO A01;

    public RunnableC11831su(C11591sO r1, AnonymousClass1t5 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        AnonymousClass1tQ r0;
        C11591sO r3 = this.A01;
        C11581sN r1 = ((AnonymousClass1sT) r3).A03;
        if (!(r1 == null || (r0 = r1.A03) == null)) {
            r0.A7J(r1);
        }
        View view = (View) ((AnonymousClass1sT) r3).A05;
        if (!(view == null || view.getWindowToken() == null)) {
            AnonymousClass1t5 r2 = this.A00;
            if (!r2.A05()) {
                if (r2.A01 != null) {
                    AnonymousClass1sZ.A00(r2, 0, 0, false, false);
                }
            }
            r3.A07 = this.A00;
        }
        r3.A05 = null;
    }
}
