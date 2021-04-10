package X;

import android.view.MenuItem;

/* renamed from: X.03Y  reason: invalid class name */
public class AnonymousClass03Y implements MenuItem.OnMenuItemClickListener {
    public final MenuItem.OnMenuItemClickListener A00;
    public final /* synthetic */ MenuItemC04230eT A01;

    public AnonymousClass03Y(MenuItemC04230eT r1, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.A01 = r1;
        this.A00 = onMenuItemClickListener;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.A00.onMenuItemClick(this.A01.A00(menuItem));
    }
}
