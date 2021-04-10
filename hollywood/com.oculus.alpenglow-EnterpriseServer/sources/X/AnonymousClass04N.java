package X;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.04N  reason: invalid class name */
public class AnonymousClass04N implements AdapterView.OnItemClickListener {
    public final /* synthetic */ AnonymousClass0Ml A00;
    public final /* synthetic */ C04110e4 A01;

    public AnonymousClass04N(AnonymousClass0Ml r1, C04110e4 r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AnonymousClass0Ml r3 = this.A00;
        C04110e4 r2 = r3.A04;
        r2.setSelection(i);
        if (r2.getOnItemClickListener() != null) {
            r2.performItemClick(view, i, r3.A00.getItemId(i));
        }
        r3.dismiss();
    }
}
