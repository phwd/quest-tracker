package defpackage;

import android.view.MenuItem;

/* renamed from: Si0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuItem$OnMenuItemClickListenerC1122Si0 implements MenuItem.OnMenuItemClickListener {
    public final MenuItem.OnMenuItemClickListener F;
    public final /* synthetic */ MenuItemC1183Ti0 G;

    public MenuItem$OnMenuItemClickListenerC1122Si0(MenuItemC1183Ti0 ti0, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.G = ti0;
        this.F = onMenuItemClickListener;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.F.onMenuItemClick(this.G.c(menuItem));
    }
}
