package X;

import android.view.Menu;

/* renamed from: X.032  reason: invalid class name */
public class AnonymousClass032 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.app.ToolbarActionBar$1";
    public final /* synthetic */ C04370em A00;

    public AnonymousClass032(C04370em r1) {
        this.A00 = r1;
    }

    public final void run() {
        C04370em r5 = this.A00;
        if (!r5.A03) {
            r5.A02.A80(new C04390ep(r5), new AnonymousClass0eo(r5));
            r5.A03 = true;
        }
        Menu A42 = r5.A02.A42();
        C04280eZ r2 = null;
        if ((A42 instanceof C04280eZ) && (r2 = (C04280eZ) A42) != null) {
            r2.A09();
        }
        try {
            A42.clear();
            if (!r5.A01.onCreatePanelMenu(0, A42) || !r5.A01.onPreparePanel(0, null, A42)) {
                A42.clear();
            }
        } finally {
            if (r2 != null) {
                r2.A08();
            }
        }
    }
}
