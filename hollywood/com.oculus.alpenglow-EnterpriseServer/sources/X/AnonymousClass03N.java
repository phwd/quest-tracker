package X;

import android.content.Context;
import android.view.MenuItem;

/* renamed from: X.03N  reason: invalid class name */
public abstract class AnonymousClass03N {
    public AnonymousClass06D<AnonymousClass08q, MenuItem> A00;
    public final Context A01;

    public final MenuItem A00(MenuItem menuItem) {
        if (!(menuItem instanceof AnonymousClass08q)) {
            return menuItem;
        }
        AnonymousClass08q r1 = (AnonymousClass08q) menuItem;
        AnonymousClass06D<AnonymousClass08q, MenuItem> r0 = this.A00;
        if (r0 == null) {
            r0 = new AnonymousClass06D<>();
            this.A00 = r0;
        }
        MenuItem menuItem2 = r0.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemC04230eT r3 = new MenuItemC04230eT(this.A01, r1);
        this.A00.put(r1, r3);
        return r3;
    }

    public AnonymousClass03N(Context context) {
        this.A01 = context;
    }
}
