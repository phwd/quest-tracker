package defpackage;

import androidx.appcompat.view.menu.ActionMenuItemView;

/* renamed from: k2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3309k2 extends AbstractView$OnTouchListenerC2013cS {
    public final /* synthetic */ ActionMenuItemView O;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3309k2(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.O = actionMenuItemView;
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public AbstractC3386kV0 b() {
        C3480l2 l2Var;
        C3651m2 m2Var = this.O.N;
        if (m2Var == null || (l2Var = m2Var.f10394a.Z) == null) {
            return null;
        }
        return l2Var.a();
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public boolean c() {
        AbstractC3386kV0 b;
        ActionMenuItemView actionMenuItemView = this.O;
        AbstractC4446qi0 qi0 = actionMenuItemView.L;
        if (qi0 == null || !qi0.a(actionMenuItemView.I) || (b = b()) == null || !b.b()) {
            return false;
        }
        return true;
    }
}
