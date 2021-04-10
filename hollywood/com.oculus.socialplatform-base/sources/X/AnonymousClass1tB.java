package X;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.1tB  reason: invalid class name */
public class AnonymousClass1tB implements AdapterView.OnItemSelectedListener {
    public final /* synthetic */ C11691sf A00;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        AnonymousClass1F5 r1;
        if (i != -1 && (r1 = this.A00.A0B) != null) {
            r1.A08 = false;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    public AnonymousClass1tB(C11691sf r1) {
        this.A00 = r1;
    }
}
