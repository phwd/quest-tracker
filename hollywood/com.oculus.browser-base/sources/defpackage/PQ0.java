package defpackage;

import android.app.Activity;
import android.view.MenuItem;

/* renamed from: PQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PQ0 implements AbstractC2012cR0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MenuItem f8690a;
    public final /* synthetic */ Activity b;
    public final /* synthetic */ QQ0 c;

    public PQ0(MenuItem menuItem, Activity activity, QQ0 qq0) {
        this.f8690a = menuItem;
        this.b = activity;
        this.c = qq0;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextChange(String str) {
        RQ0.e(this.f8690a, str, this.b);
        this.c.onQueryTextChange(str);
        return true;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextSubmit(String str) {
        return true;
    }
}
