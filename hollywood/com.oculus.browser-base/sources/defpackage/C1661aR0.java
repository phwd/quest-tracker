package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* renamed from: aR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1661aR0 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ SearchView F;

    public C1661aR0(SearchView searchView) {
        this.F = searchView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.F.t(i);
    }
}
