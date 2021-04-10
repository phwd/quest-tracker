package defpackage;

import android.view.View;

/* renamed from: xf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5628xf1 implements AbstractC5619xc1 {
    public AbstractC1834bO F;
    public final View$OnClickListenerC2109d00 G;
    public C5458wf1 H;
    public final UH0 I;

    /* renamed from: J  reason: collision with root package name */
    public final Runnable f11623J;

    public C5628xf1(UH0 uh0, View.OnClickListener onClickListener, View$OnClickListenerC2109d00 d00, boolean z, Runnable runnable) {
        this.I = uh0;
        this.f11623J = runnable;
        uh0.j(AbstractC5798yf1.f, z);
        uh0.m(AbstractC5798yf1.n, onClickListener);
        this.G = d00;
        uh0.m(AbstractC5798yf1.m, d00);
        uh0.j(AbstractC5798yf1.e, true);
        uh0.j(AbstractC5798yf1.f11692a, true);
        uh0.j(AbstractC5798yf1.g, false);
    }

    @Override // defpackage.AbstractC5619xc1
    public void a() {
    }

    @Override // defpackage.AbstractC5619xc1
    public void b() {
    }

    @Override // defpackage.AbstractC5619xc1
    public void c() {
        Runnable runnable = this.f11623J;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // defpackage.AbstractC5619xc1
    public void d() {
    }
}
