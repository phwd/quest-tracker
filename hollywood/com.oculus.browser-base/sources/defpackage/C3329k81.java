package defpackage;

import android.widget.PopupWindow;

/* renamed from: k81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3329k81 implements PopupWindow.OnDismissListener {
    public final AbstractC4448qj F;
    public final Tm1 G;
    public final String H;
    public final AbstractC0576Jj I;

    public C3329k81(AbstractC4448qj qjVar, Tm1 tm1, String str, AbstractC0576Jj jj) {
        this.F = qjVar;
        this.G = tm1;
        this.H = str;
        this.I = jj;
    }

    public void onDismiss() {
        AbstractC4448qj qjVar = this.F;
        Tm1 tm1 = this.G;
        String str = this.H;
        AbstractC0576Jj jj = this.I;
        C5638xj xjVar = (C5638xj) qjVar;
        if (xjVar.o() == 0) {
            tm1.dismissed(str);
            xjVar.r(jj);
        }
    }
}
