package defpackage;

import android.content.Context;
import android.view.MenuItem;

/* renamed from: ug  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5119ug {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11428a;
    public BW0 b;

    public AbstractC5119ug(Context context) {
        this.f11428a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof Y31)) {
            return menuItem;
        }
        Y31 y31 = (Y31) menuItem;
        if (this.b == null) {
            this.b = new BW0();
        }
        MenuItem menuItem2 = (MenuItem) this.b.getOrDefault(menuItem, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemC1183Ti0 ti0 = new MenuItemC1183Ti0(this.f11428a, y31);
        this.b.put(y31, ti0);
        return ti0;
    }
}
