package X;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p9  reason: invalid class name and case insensitive filesystem */
public class C10711p9 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ SearchView A00;

    public C10711p9(SearchView searchView) {
        this.A00 = searchView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A00.onItemClicked(i, 0, null);
    }
}
