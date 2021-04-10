package X;

import android.content.Context;
import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

/* renamed from: X.0eV  reason: invalid class name */
public class AnonymousClass0eV extends AnonymousClass0AE {
    public final ActionProvider A00;
    public final /* synthetic */ MenuItemC04230eT A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eV(MenuItemC04230eT r1, Context context, ActionProvider actionProvider) {
        super(context);
        this.A01 = r1;
        this.A00 = actionProvider;
    }

    @Override // X.AnonymousClass0AE
    public final View A00() {
        return this.A00.onCreateActionView();
    }

    @Override // X.AnonymousClass0AE
    public final void A02(SubMenu subMenu) {
        this.A00.onPrepareSubMenu(subMenu);
    }

    @Override // X.AnonymousClass0AE
    public final boolean A04() {
        return this.A00.hasSubMenu();
    }

    @Override // X.AnonymousClass0AE
    public final boolean A06() {
        return this.A00.onPerformDefaultAction();
    }
}
