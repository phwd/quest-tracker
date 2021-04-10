package defpackage;

import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* renamed from: XQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class XQ0 implements View.OnClickListener {
    public final /* synthetic */ SearchView F;

    public XQ0(SearchView searchView) {
        this.F = searchView;
    }

    public void onClick(View view) {
        SearchView searchView = this.F;
        if (view == searchView.c0) {
            searchView.u();
        } else if (view == searchView.e0) {
            searchView.s();
        } else if (view == searchView.d0) {
            searchView.v();
        } else if (view == searchView.f0) {
            Objects.requireNonNull(searchView);
        } else if (view == searchView.V) {
            searchView.r();
        }
    }
}
