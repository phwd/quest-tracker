package X;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05H  reason: invalid class name */
public class AnonymousClass05H implements AdapterView.OnItemClickListener {
    public final /* synthetic */ SearchView A00;

    public AnonymousClass05H(SearchView searchView) {
        this.A00 = searchView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.A00.onItemClicked(i, 0, null);
    }
}
