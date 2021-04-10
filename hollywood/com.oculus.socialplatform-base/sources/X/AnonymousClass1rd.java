package X;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1rd  reason: invalid class name */
public class AnonymousClass1rd extends C05430vh {
    public final /* synthetic */ C11201rK A00;

    public AnonymousClass1rd(C11201rK r1) {
        this.A00 = r1;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6i(View view) {
        View view2;
        C11201rK r3 = this.A00;
        if (r3.A0D && (view2 = r3.A03) != null) {
            view2.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            r3.A08.setTranslationY(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        }
        r3.A08.setVisibility(8);
        r3.A08.setTransitioning(false);
        r3.A07 = null;
        AbstractC11461s1 r1 = r3.A05;
        if (r1 != null) {
            r1.A6z(r3.A06);
            r3.A06 = null;
            r3.A05 = null;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = r3.A0A;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.requestApplyInsets();
        }
    }
}
