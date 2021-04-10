package X;

import android.view.MenuItem;

/* renamed from: X.1t3  reason: invalid class name */
public class AnonymousClass1t3 implements Runnable {
    public static final String __redex_internal_original_name = "androidx.appcompat.view.menu.CascadingMenuPopup$3$1";
    public final /* synthetic */ MenuItem A00;
    public final /* synthetic */ C11851sw A01;
    public final /* synthetic */ AnonymousClass1tW A02;
    public final /* synthetic */ C11581sN A03;

    public AnonymousClass1t3(C11851sw r1, AnonymousClass1tW r2, MenuItem menuItem, C11581sN r4) {
        this.A01 = r1;
        this.A02 = r2;
        this.A00 = menuItem;
        this.A03 = r4;
    }

    public final void run() {
        AnonymousClass1tW r1 = this.A02;
        if (r1 != null) {
            View$OnKeyListenerC11671sd r2 = this.A01.A00;
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
