package defpackage;

import android.view.MenuItem;

/* renamed from: Ri0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuItem$OnActionExpandListenerC1061Ri0 implements MenuItem.OnActionExpandListener {

    /* renamed from: a  reason: collision with root package name */
    public final MenuItem.OnActionExpandListener f8847a;
    public final /* synthetic */ MenuItemC1183Ti0 b;

    public MenuItem$OnActionExpandListenerC1061Ri0(MenuItemC1183Ti0 ti0, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.b = ti0;
        this.f8847a = onActionExpandListener;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f8847a.onMenuItemActionCollapse(this.b.c(menuItem));
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f8847a.onMenuItemActionExpand(this.b.c(menuItem));
    }
}
