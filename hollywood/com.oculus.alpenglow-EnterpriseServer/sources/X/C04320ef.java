package X;

import androidx.appcompat.view.menu.ActionMenuItemView;

/* renamed from: X.0ef  reason: invalid class name and case insensitive filesystem */
public class C04320ef extends AbstractView$OnAttachStateChangeListenerC003504l {
    public final /* synthetic */ ActionMenuItemView A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04320ef(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.A00 = actionMenuItemView;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final AbstractC000903e A01() {
        AnonymousClass03M r0 = this.A00.A01;
        if (r0 != null) {
            return r0.A00();
        }
        return null;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final boolean A02() {
        AbstractC000903e A01;
        ActionMenuItemView actionMenuItemView = this.A00;
        AnonymousClass03W r2 = actionMenuItemView.A02;
        if (r2 == null || !r2.A5L(actionMenuItemView.A00) || (A01 = A01()) == null || !A01.A5a()) {
            return false;
        }
        return true;
    }
}
