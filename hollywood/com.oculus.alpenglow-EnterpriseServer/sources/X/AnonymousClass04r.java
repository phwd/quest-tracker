package X;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.04r  reason: invalid class name */
public class AnonymousClass04r implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ C04080dy A00;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        C003004g r1;
        if (i != -1 && (r1 = this.A00.A0B) != null) {
            r1.A08 = false;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public AnonymousClass04r(C04080dy r1) {
        this.A00 = r1;
    }
}
