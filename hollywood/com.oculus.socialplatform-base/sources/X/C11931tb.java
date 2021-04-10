package X;

import android.content.Context;
import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;

/* renamed from: X.1tb  reason: invalid class name and case insensitive filesystem */
public class C11931tb extends AbstractC002906y {
    public final ActionProvider A00;
    public final /* synthetic */ MenuItemC11611sU A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11931tb(MenuItemC11611sU r1, Context context, ActionProvider actionProvider) {
        super(context);
        this.A01 = r1;
        this.A00 = actionProvider;
    }

    @Override // X.AbstractC002906y
    public final View A00() {
        return this.A00.onCreateActionView();
    }

    @Override // X.AbstractC002906y
    public final void A02(SubMenu subMenu) {
        this.A00.onPrepareSubMenu(subMenu);
    }

    @Override // X.AbstractC002906y
    public final boolean A04() {
        return this.A00.hasSubMenu();
    }

    @Override // X.AbstractC002906y
    public final boolean A06() {
        return this.A00.onPerformDefaultAction();
    }
}
