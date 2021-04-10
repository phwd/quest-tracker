package X;

import androidx.appcompat.widget.ActionBarOverlayLayout;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1En  reason: invalid class name */
public class AnonymousClass1En implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.widget.ActionBarOverlayLayout$2";
    public final /* synthetic */ ActionBarOverlayLayout A00;

    public AnonymousClass1En(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.A00 = actionBarOverlayLayout;
    }

    public final void run() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.A00;
        actionBarOverlayLayout.A03();
        actionBarOverlayLayout.A00 = actionBarOverlayLayout.A03.animate().translationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z).setListener(actionBarOverlayLayout.A0K);
    }
}
