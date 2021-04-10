package defpackage;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: Y3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y3 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ C2120d4 F;
    public final /* synthetic */ C1598a4 G;

    public Y3(C1598a4 a4Var, C2120d4 d4Var) {
        this.G = a4Var;
        this.F = d4Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.G.p.onClick(this.F.b, i);
        if (!this.G.u) {
            this.F.b.dismiss();
        }
    }
}
