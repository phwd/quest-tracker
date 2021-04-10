package defpackage;

/* renamed from: g30  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2630g30 {
    public final AbstractC2972i30 a(AbstractC1552Zj0 zj0, int i) {
        PN0 pn0 = new PN0(zj0);
        SA l = zj0.l();
        AbstractC2972i30 d = d(l, new C2889hc(l, pn0));
        C3004iE iEVar = new C3004iE();
        iEVar.F.add(d);
        pn0.F.f10037J = iEVar;
        pn0.g0();
        ((C2288e30) d.i()).I = i;
        return d;
    }

    public final LN0 b(AbstractC3313k30 k30, B30 b30) {
        return c(k30, b30.x());
    }

    public LN0 c(AbstractC3313k30 k30, AbstractC1552Zj0 zj0) {
        PN0 pn0 = new PN0(zj0);
        SA l = zj0.l();
        pn0.Y(k30);
        pn0.f0(e(l, k30));
        pn0.g0();
        return pn0;
    }

    public abstract AbstractC2972i30 d(SA sa, AbstractC3426kk0 kk0);

    public abstract AbstractC3142j30 e(SA sa, AbstractC3313k30 k30);

    public abstract String f();

    public abstract int g();
}
