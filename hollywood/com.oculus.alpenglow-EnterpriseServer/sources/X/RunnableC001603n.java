package X;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.03n  reason: invalid class name and case insensitive filesystem */
public class RunnableC001603n implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionBarOverlayLayout$2";
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public RunnableC001603n(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.A00 = actionBarOverlayLayout;
    }

    public final void run() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A03();
        actionBarOverlayLayout.A00 = actionBarOverlayLayout.A03.animate().translationY(0.0f).setListener(actionBarOverlayLayout.A0K);
    }
}
