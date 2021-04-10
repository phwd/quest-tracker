package X;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.05I  reason: invalid class name */
public class AnonymousClass05I implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ SearchView A00;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public AnonymousClass05I(SearchView searchView) {
        this.A00 = searchView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.A00.onItemSelected(i);
    }
}
