package X;

import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.05Z  reason: invalid class name */
public class AnonymousClass05Z implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.Toolbar$2";
    public final /* synthetic */ Toolbar A00;

    public AnonymousClass05Z(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    public final void run() {
        AnonymousClass0Mm r0;
        ActionMenuView actionMenuView = this.A00.A09;
        if (actionMenuView != null && (r0 = actionMenuView.A04) != null) {
            r0.A07();
        }
    }
}
