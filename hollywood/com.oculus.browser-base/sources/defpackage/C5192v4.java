package defpackage;

import android.widget.SearchView;
import org.chromium.base.Callback;

/* renamed from: v4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5192v4 implements SearchView.OnQueryTextListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f11458a;

    public C5192v4(C5362w4 w4Var, Callback callback) {
        this.f11458a = callback;
    }

    public boolean onQueryTextChange(String str) {
        this.f11458a.onResult(str);
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }
}
