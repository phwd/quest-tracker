package X;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0eh  reason: invalid class name */
public class AnonymousClass0eh implements AnonymousClass03C {
    public final ActionMode.Callback A00;
    public final ArrayList<AnonymousClass03G> A01 = new ArrayList<>();
    public final Context A02;
    public final AnonymousClass06D<Menu, Menu> A03 = new AnonymousClass06D<>();

    public final ActionMode A00(AnonymousClass03D r6) {
        ArrayList<AnonymousClass03G> arrayList = this.A01;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass03G r1 = arrayList.get(i);
            if (r1 != null && r1.A01 == r6) {
                return r1;
            }
        }
        AnonymousClass03G r0 = new AnonymousClass03G(this.A02, r6);
        arrayList.add(r0);
        return r0;
    }

    @Override // X.AnonymousClass03C
    public final boolean A5o(AnonymousClass03D r5, MenuItem menuItem) {
        return this.A00.onActionItemClicked(A00(r5), new MenuItemC04230eT(this.A02, (AnonymousClass08q) menuItem));
    }

    @Override // X.AnonymousClass03C
    public final boolean A62(AnonymousClass03D r7, Menu menu) {
        ActionMode.Callback callback = this.A00;
        ActionMode A002 = A00(r7);
        AnonymousClass06D<Menu, Menu> r3 = this.A03;
        Menu menu2 = r3.get(menu);
        if (menu2 == null) {
            menu2 = new AnonymousClass0eQ(this.A02, (AbstractMenuC007608p) menu);
            r3.put(menu, menu2);
        }
        return callback.onCreateActionMode(A002, menu2);
    }

    @Override // X.AnonymousClass03C
    public final void A63(AnonymousClass03D r3) {
        this.A00.onDestroyActionMode(A00(r3));
    }

    @Override // X.AnonymousClass03C
    public final boolean A6N(AnonymousClass03D r7, Menu menu) {
        ActionMode.Callback callback = this.A00;
        ActionMode A002 = A00(r7);
        AnonymousClass06D<Menu, Menu> r3 = this.A03;
        Menu menu2 = r3.get(menu);
        if (menu2 == null) {
            menu2 = new AnonymousClass0eQ(this.A02, (AbstractMenuC007608p) menu);
            r3.put(menu, menu2);
        }
        return callback.onPrepareActionMode(A002, menu2);
    }

    public AnonymousClass0eh(Context context, ActionMode.Callback callback) {
        this.A02 = context;
        this.A00 = callback;
    }
}
