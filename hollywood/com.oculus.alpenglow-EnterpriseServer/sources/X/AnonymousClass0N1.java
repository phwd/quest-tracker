package X;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;

/* renamed from: X.0N1  reason: invalid class name */
public class AnonymousClass0N1 extends C03750dB {
    public final /* synthetic */ C04340ej A00;

    public AnonymousClass0N1(C04340ej r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5q(View view) {
        View view2;
        C04340ej r3 = this.A00;
        if (r3.A0C && (view2 = r3.A05) != null) {
            view2.setTranslationY(0.0f);
            r3.A09.setTranslationY(0.0f);
        }
        r3.A09.setVisibility(8);
        r3.A09.setTransitioning(false);
        r3.A08 = null;
        AnonymousClass03C r1 = r3.A06;
        if (r1 != null) {
            r1.A63(r3.A07);
            r3.A07 = null;
            r3.A06 = null;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = r3.A0A;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.requestApplyInsets();
        }
    }
}
