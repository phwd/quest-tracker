package defpackage;

import android.view.View;

/* renamed from: Rc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1043Rc0 implements View.OnClickListener {
    public final /* synthetic */ C0337Fl0 F;
    public final /* synthetic */ C1104Sc0 G;

    public View$OnClickListenerC1043Rc0(C1104Sc0 sc0, C0337Fl0 fl0) {
        this.G = sc0;
        this.F = fl0;
    }

    public void onClick(View view) {
        int l1 = this.G.e1().l1() - 1;
        if (l1 >= 0) {
            this.G.g1(this.F.s(l1));
        }
    }
}
