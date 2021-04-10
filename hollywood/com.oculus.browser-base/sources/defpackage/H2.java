package defpackage;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.mediarouter.app.MediaRouteActionProvider;

/* renamed from: H2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class H2 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8130a;
    public C0756Mi0 b;

    public H2(Context context) {
        this.f8130a = context;
    }

    public boolean a() {
        return false;
    }

    public abstract boolean b();

    public abstract View c();

    public View d(MenuItem menuItem) {
        return c();
    }

    public abstract boolean e();

    public void f(SubMenu subMenu) {
    }

    public boolean g() {
        return this instanceof MediaRouteActionProvider;
    }

    public void h(C0756Mi0 mi0) {
        if (this.b != null) {
            StringBuilder i = AbstractC2531fV.i("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            i.append(getClass().getSimpleName());
            i.append(" instance while it is still in use somewhere else?");
            Log.w("ActionProvider(support)", i.toString());
        }
        this.b = mi0;
    }
}
