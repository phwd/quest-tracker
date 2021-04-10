package defpackage;

import android.app.Activity;
import android.view.MenuItem;

/* renamed from: LQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LQ0 implements MenuItem.OnMenuItemClickListener {
    public final MenuItem F;
    public final Activity G;
    public final QQ0 H;

    public LQ0(MenuItem menuItem, Activity activity, QQ0 qq0) {
        this.F = menuItem;
        this.G = activity;
        this.H = qq0;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        MenuItem menuItem2 = this.F;
        Activity activity = this.G;
        QQ0 qq0 = this.H;
        RQ0.e(menuItem2, "", activity);
        qq0.onQueryTextChange("");
        return false;
    }
}
