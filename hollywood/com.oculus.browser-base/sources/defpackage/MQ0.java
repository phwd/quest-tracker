package defpackage;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: MQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MQ0 implements View.OnClickListener {
    public final SearchView F;
    public final MenuItem G;
    public final Activity H;
    public final QQ0 I;

    public MQ0(SearchView searchView, MenuItem menuItem, Activity activity, QQ0 qq0) {
        this.F = searchView;
        this.G = menuItem;
        this.H = activity;
        this.I = qq0;
    }

    public void onClick(View view) {
        SearchView searchView = this.F;
        MenuItem menuItem = this.G;
        Activity activity = this.H;
        QQ0 qq0 = this.I;
        searchView.x("", false);
        RQ0.e(menuItem, "", activity);
        qq0.onQueryTextChange("");
    }
}
