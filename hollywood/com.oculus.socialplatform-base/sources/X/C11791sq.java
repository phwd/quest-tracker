package X;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.1sq  reason: invalid class name and case insensitive filesystem */
public class C11791sq implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C11701sg A00;
    public final /* synthetic */ AnonymousClass1sR A01;

    public C11791sq(C11701sg r1, AnonymousClass1sR r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C11701sg r3 = this.A00;
        AnonymousClass1sR r2 = r3.A04;
        r2.setSelection(i);
        if (r2.getOnItemClickListener() != null) {
            r2.performItemClick(view, i, r3.A00.getItemId(i));
        }
        r3.dismiss();
    }
}
