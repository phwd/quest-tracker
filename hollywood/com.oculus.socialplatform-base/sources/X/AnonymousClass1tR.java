package X;

import android.view.MenuItem;

/* renamed from: X.1tR  reason: invalid class name */
public class AnonymousClass1tR implements MenuItem.OnActionExpandListener {
    public final MenuItem.OnActionExpandListener A00;
    public final /* synthetic */ MenuItemC11611sU A01;

    public AnonymousClass1tR(MenuItemC11611sU r1, MenuItem.OnActionExpandListener onActionExpandListener) {
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
