package defpackage;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertController$RecycleListView;

/* renamed from: Z3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z3 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ AlertController$RecycleListView F;
    public final /* synthetic */ C2120d4 G;
    public final /* synthetic */ C1598a4 H;

    public Z3(C1598a4 a4Var, AlertController$RecycleListView alertController$RecycleListView, C2120d4 d4Var) {
        this.H = a4Var;
        this.F = alertController$RecycleListView;
        this.G = d4Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        boolean[] zArr = this.H.s;
        if (zArr != null) {
            zArr[i] = this.F.isItemChecked(i);
        }
        this.H.w.onClick(this.G.b, i, this.F.isItemChecked(i));
    }
}
