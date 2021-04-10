package X;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.02c  reason: invalid class name */
public class AnonymousClass02c implements AdapterView.OnItemClickListener {
    public final /* synthetic */ AnonymousClass02e A00;
    public final /* synthetic */ AnonymousClass02i A01;

    public AnonymousClass02c(AnonymousClass02e r1, AnonymousClass02i r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AnonymousClass02e r3 = this.A00;
        DialogInterface.OnClickListener onClickListener = r3.A01;
        DialogC04410er r1 = this.A01.A0X;
        onClickListener.onClick(r1, i);
        if (!r3.A07) {
            r1.dismiss();
        }
    }
}
