package X;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1ra  reason: invalid class name */
public class AnonymousClass1ra implements AbstractC11461s1 {
    public final ActionMode.Callback A00;
    public final ArrayList<C11291rj> A01 = new ArrayList<>();
    public final Context A02;
    public final C000502v<Menu, Menu> A03 = new C000502v<>();

    public final ActionMode A00(AbstractC11301rk r6) {
        ArrayList<C11291rj> arrayList = this.A01;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C11291rj r1 = arrayList.get(i);
            if (r1 != null && r1.A01 == r6) {
                return r1;
            }
        }
        C11291rj r0 = new C11291rj(this.A02, r6);
        arrayList.add(r0);
        return r0;
    }

    @Override // X.AbstractC11461s1
    public final boolean A6g(AbstractC11301rk r5, MenuItem menuItem) {
        return this.A00.onActionItemClicked(A00(r5), new MenuItemC11611sU(this.A02, (AnonymousClass05Y) menuItem));
    }

    @Override // X.AbstractC11461s1
    public final boolean A6u(AbstractC11301rk r7, Menu menu) {
        ActionMode.Callback callback = this.A00;
        ActionMode A002 = A00(r7);
        C000502v<Menu, Menu> r3 = this.A03;
        Menu menu2 = r3.get(menu);
        if (menu2 == null) {
            menu2 = new AnonymousClass1rg(this.A02, (AnonymousClass05X) menu);
            r3.put(menu, menu2);
        }
        return callback.onCreateActionMode(A002, menu2);
    }

    @Override // X.AbstractC11461s1
    public final void A6z(AbstractC11301rk r3) {
        this.A00.onDestroyActionMode(A00(r3));
    }

    @Override // X.AbstractC11461s1
    public final boolean A7V(AbstractC11301rk r7, Menu menu) {
        ActionMode.Callback callback = this.A00;
        ActionMode A002 = A00(r7);
        C000502v<Menu, Menu> r3 = this.A03;
        Menu menu2 = r3.get(menu);
        if (menu2 == null) {
            menu2 = new AnonymousClass1rg(this.A02, (AnonymousClass05X) menu);
            r3.put(menu, menu2);
        }
        return callback.onPrepareActionMode(A002, menu2);
    }

    public AnonymousClass1ra(Context context, ActionMode.Callback callback) {
        this.A02 = context;
        this.A00 = callback;
    }
}
