package X;

import android.view.Menu;
import androidx.appcompat.widget.ActionBarContextView;

/* renamed from: X.1rk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11301rk {
    public Object A00;
    public boolean A01;

    public final Menu A00() {
        if (!(this instanceof AnonymousClass1rQ)) {
            return ((AnonymousClass1rN) this).A03;
        }
        return ((AnonymousClass1rQ) this).A02;
    }

    public final void A01() {
        if (!(this instanceof AnonymousClass1rQ)) {
            AnonymousClass1rN r4 = (AnonymousClass1rN) this;
            C11201rK r3 = r4.A04;
            if (r3.A04 == r4) {
                if (r3.A0F) {
                    r3.A06 = r4;
                    r3.A05 = r4.A00;
                } else {
                    r4.A00.A6z(r4);
                }
                r4.A00 = null;
                r3.A06(false);
                ActionBarContextView actionBarContextView = r3.A09;
                if (actionBarContextView.A00 == null) {
                    actionBarContextView.removeAllViews();
                    actionBarContextView.A01 = null;
                    ((AnonymousClass1rP) actionBarContextView).A01 = null;
                }
                r3.A0B.A5I().sendAccessibilityEvent(32);
                r3.A0A.setHideOnContentScrollEnabled(r3.A0G);
                r3.A04 = null;
                return;
            }
            return;
        }
        AnonymousClass1rQ r2 = (AnonymousClass1rQ) this;
        if (!r2.A05) {
            r2.A05 = true;
            r2.A03.sendAccessibilityEvent(32);
            r2.A01.A6z(r2);
        }
    }

    public final void A02() {
        if (!(this instanceof AnonymousClass1rQ)) {
            AnonymousClass1rN r2 = (AnonymousClass1rN) this;
            if (r2.A04.A04 == r2) {
                C11581sN r1 = r2.A03;
                r1.A09();
                try {
                    r2.A00.A7V(r2, r1);
                } finally {
                    r1.A08();
                }
            }
        } else {
            AnonymousClass1rQ r22 = (AnonymousClass1rQ) this;
            r22.A01.A7V(r22, r22.A02);
        }
    }

    public final void A03(CharSequence charSequence) {
        ActionBarContextView actionBarContextView;
        if (!(this instanceof AnonymousClass1rQ)) {
            actionBarContextView = ((AnonymousClass1rN) this).A04.A09;
        } else {
            actionBarContextView = ((AnonymousClass1rQ) this).A03;
        }
        actionBarContextView.setSubtitle(charSequence);
    }

    public final void A04(CharSequence charSequence) {
        ActionBarContextView actionBarContextView;
        if (!(this instanceof AnonymousClass1rQ)) {
            actionBarContextView = ((AnonymousClass1rN) this).A04.A09;
        } else {
            actionBarContextView = ((AnonymousClass1rQ) this).A03;
        }
        actionBarContextView.setTitle(charSequence);
    }

    public void A05(boolean z) {
        this.A01 = z;
    }
}
