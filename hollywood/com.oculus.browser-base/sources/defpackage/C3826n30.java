package defpackage;

import org.chromium.mojo.system.impl.CoreImpl;

/* renamed from: n30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3826n30 implements AbstractC0543Ix {
    public SA F;
    public C4509r30 G;

    public C3826n30(AbstractC1552Zj0 zj0) {
        QW qw = (QW) zj0;
        this.F = qw.G;
        int i = AbstractC3997o30.s;
        PN0 pn0 = new PN0(zj0);
        CoreImpl coreImpl = qw.G;
        C4509r30 r30 = new C4509r30(coreImpl, new C2889hc(coreImpl, pn0));
        C3004iE iEVar = new C3004iE();
        iEVar.F.add(r30);
        pn0.F.f10037J = iEVar;
        pn0.g0();
        C2288e30 e30 = r30.F;
        e30.I = 0;
        this.G = r30;
        e30.H = this;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.G.close();
    }

    public AbstractC2972i30 a(AbstractC2630g30 g30) {
        C1576Zv0 b = ((CoreImpl) this.F).b(null);
        AbstractC2972i30 a2 = g30.a((AbstractC1552Zj0) b.f9384a, 0);
        this.G.C(g30.f(), ((AbstractC1552Zj0) b.b).N());
        return a2;
    }
}
