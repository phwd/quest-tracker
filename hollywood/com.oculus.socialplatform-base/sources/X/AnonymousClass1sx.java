package X;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: X.1sx  reason: invalid class name */
public class AnonymousClass1sx implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C11741sl A00;
    public final /* synthetic */ C11731sk A01;

    public AnonymousClass1sx(C11741sl r1, C11731sk r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C11741sl r3 = this.A00;
        DialogInterface.OnClickListener onClickListener = r3.A01;
        DialogC11221rS r1 = this.A01.A0X;
        onClickListener.onClick(r1, i);
        if (!r3.A07) {
            r1.dismiss();
        }
    }
}
