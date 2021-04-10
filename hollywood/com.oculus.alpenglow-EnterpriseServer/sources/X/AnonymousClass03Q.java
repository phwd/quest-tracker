package X;

import android.view.MenuItem;

/* renamed from: X.03Q  reason: invalid class name */
public class AnonymousClass03Q implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.view.menu.CascadingMenuPopup$3$1";
    public final /* synthetic */ MenuItem A00;
    public final /* synthetic */ C04300ed A01;
    public final /* synthetic */ AnonymousClass03R A02;
    public final /* synthetic */ C04280eZ A03;

    public AnonymousClass03Q(C04300ed r1, AnonymousClass03R r2, MenuItem menuItem, C04280eZ r4) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = menuItem;
        this.A03 = r4;
    }

    public final void run() {
        AnonymousClass03R r1 = this.A02;
        if (r1 != null) {
            View$OnKeyListenerC01900Mx r2 = this.A01.A00;
            r2.A01 = true;
            r1.A01.A0F(false);
            r2.A01 = false;
        }
        MenuItem menuItem = this.A00;
        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
            this.A03.A0K(menuItem, null, 4);
        }
    }
}
