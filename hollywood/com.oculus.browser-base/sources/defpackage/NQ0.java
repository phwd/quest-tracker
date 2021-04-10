package defpackage;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;

/* renamed from: NQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NQ0 implements View.OnLayoutChangeListener {
    public final MenuItem F;
    public final SearchView G;
    public final Activity H;

    public NQ0(MenuItem menuItem, SearchView searchView, Activity activity) {
        this.F = menuItem;
        this.G = searchView;
        this.H = activity;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        MenuItem menuItem = this.F;
        SearchView searchView = this.G;
        RQ0.e(menuItem, searchView.V.getText().toString(), this.H);
    }
}
