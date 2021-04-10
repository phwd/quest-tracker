package X;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.03o  reason: invalid class name and case insensitive filesystem */
public class RunnableC001703o implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionBarOverlayLayout$3";
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public RunnableC001703o(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.A00 = actionBarOverlayLayout;
    }

    public final void run() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A03();
        actionBarOverlayLayout.A00 = actionBarOverlayLayout.A03.animate().translationY((float) (-actionBarOverlayLayout.A03.getHeight())).setListener(actionBarOverlayLayout.A0K);
    }
}
