package defpackage;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

/* renamed from: R31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R31 implements AbstractC5526x2 {

    /* renamed from: a  reason: collision with root package name */
    public final ActionMode.Callback f8809a;
    public final Context b;
    public final ArrayList c = new ArrayList();
    public final BW0 d = new BW0();

    public R31(Context context, ActionMode.Callback callback) {
        this.b = context;
        this.f8809a = callback;
    }

    @Override // defpackage.AbstractC5526x2
    public boolean a(AbstractC5696y2 y2Var, Menu menu) {
        return this.f8809a.onCreateActionMode(e(y2Var), f(menu));
    }

    @Override // defpackage.AbstractC5526x2
    public boolean b(AbstractC5696y2 y2Var, MenuItem menuItem) {
        return this.f8809a.onActionItemClicked(e(y2Var), new MenuItemC1183Ti0(this.b, (Y31) menuItem));
    }

    @Override // defpackage.AbstractC5526x2
    public boolean c(AbstractC5696y2 y2Var, Menu menu) {
        return this.f8809a.onPrepareActionMode(e(y2Var), f(menu));
    }

    @Override // defpackage.AbstractC5526x2
    public void d(AbstractC5696y2 y2Var) {
        this.f8809a.onDestroyActionMode(e(y2Var));
    }

    public ActionMode e(AbstractC5696y2 y2Var) {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            S31 s31 = (S31) this.c.get(i);
            if (s31 != null && s31.b == y2Var) {
                return s31;
            }
        }
        S31 s312 = new S31(this.b, y2Var);
        this.c.add(s312);
        return s312;
    }

    public final Menu f(Menu menu) {
        Menu menu2 = (Menu) this.d.getOrDefault(menu, null);
        if (menu2 != null) {
            return menu2;
        }
        MenuC2569fj0 fj0 = new MenuC2569fj0(this.b, (U31) menu);
        this.d.put(menu, fj0);
        return fj0;
    }
}
