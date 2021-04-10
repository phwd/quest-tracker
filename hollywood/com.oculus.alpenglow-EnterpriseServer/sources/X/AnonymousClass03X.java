package X;

import android.view.MenuItem;

/* renamed from: X.03X  reason: invalid class name */
public class AnonymousClass03X implements MenuItem.OnActionExpandListener {
    public final MenuItem.OnActionExpandListener A00;
    public final /* synthetic */ MenuItemC04230eT A01;

    public AnonymousClass03X(MenuItemC04230eT r1, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.A01 = r1;
        this.A00 = onActionExpandListener;
    }

    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.A00.onMenuItemActionCollapse(this.A01.A00(menuItem));
    }

    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.A00.onMenuItemActionExpand(this.A01.A00(menuItem));
    }
}
