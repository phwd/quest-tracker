package X;

import android.view.Menu;

/* renamed from: X.1rf  reason: invalid class name */
public class AnonymousClass1rf implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.app.ToolbarActionBar$1";
    public final /* synthetic */ AnonymousClass1rR A00;

    public AnonymousClass1rf(AnonymousClass1rR r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1rR r5 = this.A00;
        if (!r5.A05) {
            r5.A02.AA0(new C11371rs(r5), new C11351rq(r5));
            r5.A05 = true;
        }
        Menu A4O = r5.A02.A4O();
        C11581sN r2 = null;
        if ((A4O instanceof C11581sN) && (r2 = (C11581sN) A4O) != null) {
            r2.A09();
        }
        try {
            A4O.clear();
            if (!r5.A01.onCreatePanelMenu(0, A4O) || !r5.A01.onPreparePanel(0, null, A4O)) {
                A4O.clear();
            }
        } finally {
            if (r2 != null) {
                r2.A08();
            }
        }
    }
}
