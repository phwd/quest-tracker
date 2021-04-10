package X;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* renamed from: X.1p8  reason: invalid class name and case insensitive filesystem */
public class C10701p8 implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ SearchView A00;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public C10701p8(SearchView searchView) {
        this.A00 = searchView;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.A00.onItemSelected(i);
    }
}
