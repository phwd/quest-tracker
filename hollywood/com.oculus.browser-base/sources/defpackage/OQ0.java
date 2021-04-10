package defpackage;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;

/* renamed from: OQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OQ0 implements View.OnClickListener {
    public final MenuItem F;
    public final Activity G;
    public final QQ0 H;

    public OQ0(MenuItem menuItem, Activity activity, QQ0 qq0) {
        this.F = menuItem;
        this.G = activity;
        this.H = qq0;
    }

    public void onClick(View view) {
        MenuItem menuItem = this.F;
        Activity activity = this.G;
        QQ0 qq0 = this.H;
        RQ0.e(menuItem, "", activity);
        qq0.onQueryTextChange("");
    }
}
