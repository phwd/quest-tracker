package defpackage;

import android.view.View;

/* renamed from: Uc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1227Uc1 implements AbstractC5619xc1 {
    public final /* synthetic */ C1349Wc1 F;

    public C1227Uc1(C1349Wc1 wc1) {
        this.F = wc1;
    }

    @Override // defpackage.AbstractC5619xc1
    public void a() {
        XK0 H;
        AbstractC0124Ca1 ca1 = this.F.M;
        if (((AbstractC0246Ea1) ca1).h && (H = this.F.I.G.H(((AbstractC0246Ea1) ca1).c.d().index())) != null) {
            View view = H.G;
            view.requestFocus();
            view.sendAccessibilityEvent(8);
        }
    }

    @Override // defpackage.AbstractC5619xc1
    public void b() {
    }

    @Override // defpackage.AbstractC5619xc1
    public void c() {
    }

    @Override // defpackage.AbstractC5619xc1
    public void d() {
    }
}
