package X;

import android.view.MenuItem;

/* renamed from: X.1tX  reason: invalid class name */
public class AnonymousClass1tX implements MenuItem.OnMenuItemClickListener {
    public final MenuItem.OnMenuItemClickListener A00;
    public final /* synthetic */ MenuItemC11611sU A01;

    public AnonymousClass1tX(MenuItemC11611sU r1, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.A01 = r1;
        this.A00 = onMenuItemClickListener;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.A00.onMenuItemClick(this.A01.A00(menuItem));
    }
}
