package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: E8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E8 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ H8 F;

    public E8(H8 h8, AppCompatSpinner appCompatSpinner) {
        this.F = h8;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.F.l0.setSelection(i);
        if (this.F.l0.getOnItemClickListener() != null) {
            H8 h8 = this.F;
            h8.l0.performItemClick(view, i, h8.i0.getItemId(i));
        }
        this.F.dismiss();
    }
}
