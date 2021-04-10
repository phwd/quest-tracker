package X;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.1Eo  reason: invalid class name */
public class AnonymousClass1Eo implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionBarOverlayLayout$3";
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public AnonymousClass1Eo(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.A00 = actionBarOverlayLayout;
    }

    public final void run() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A03();
        actionBarOverlayLayout.A00 = actionBarOverlayLayout.A03.animate().translationY((float) (-actionBarOverlayLayout.A03.getHeight())).setListener(actionBarOverlayLayout.A0K);
    }
}
