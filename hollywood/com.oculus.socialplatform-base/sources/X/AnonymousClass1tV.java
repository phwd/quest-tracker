package X;

import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.1tV  reason: invalid class name */
public class AnonymousClass1tV implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.Toolbar$2";
    public final /* synthetic */ Toolbar A00;

    public AnonymousClass1tV(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    public final void run() {
        C11591sO r0;
        ActionMenuView actionMenuView = this.A00.A09;
        if (actionMenuView != null && (r0 = actionMenuView.A04) != null) {
            r0.A05();
        }
    }
}
