package X;

import android.content.Context;
import android.view.MenuItem;

/* renamed from: X.1rv  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11401rv {
    public C000502v<AnonymousClass05Y, MenuItem> A00;
    public final Context A01;

    public final MenuItem A00(MenuItem menuItem) {
        if (!(menuItem instanceof AnonymousClass05Y)) {
            return menuItem;
        }
        AnonymousClass05Y r1 = (AnonymousClass05Y) menuItem;
        C000502v<AnonymousClass05Y, MenuItem> r0 = this.A00;
        if (r0 == null) {
            r0 = new C000502v<>();
            this.A00 = r0;
        }
        MenuItem menuItem2 = r0.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemC11611sU r3 = new MenuItemC11611sU(this.A01, r1);
        this.A00.put(r1, r3);
        return r3;
    }

    public AbstractC11401rv(Context context) {
        this.A01 = context;
    }
}
