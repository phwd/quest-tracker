package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: s50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4686s50 implements AbstractC1641aI0, AbstractC2504fI0, M50 {
    public final UH0 F;
    public final AbstractC2124d50 G;
    public final AbstractC1953c50 H;

    public C4686s50(UH0 uh0, AbstractC2124d50 d50, AbstractC1953c50 c50, L50 l50) {
        this.F = uh0;
        this.G = d50;
        this.H = c50;
        uh0.m(I50.j, new C4003o50(this));
        uh0.m(I50.h, new RunnableC4174p50(this));
        TH0 th0 = I50.g;
        uh0.m(th0, new H50(l50));
        if (N.M09VlOh_("AutofillKeyboardAccessory")) {
            ((C1794b90) uh0.g(I50.f8198a)).q((G50) uh0.g(th0));
        }
        uh0.f9530a.b(this);
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        KH0 kh0 = (KH0) obj;
        QH0 qh0 = I50.b;
        if (kh0 == qh0) {
            this.F.j(I50.k, false);
            ((R50) this.H).d();
            if (!this.F.h(qh0)) {
                a(0, new C2636g50[0]);
            }
        } else if (kh0 == I50.f) {
            C3148j50 e = ((R50) this.H).e();
            if (e != null) {
                this.F.m(I50.e, e.f10187a);
            }
        } else if (kh0 != I50.d && kh0 != I50.h && kh0 != I50.g && kh0 != I50.e && kh0 != I50.c && kh0 != I50.i && kh0 != I50.j) {
            QH0 qh02 = I50.k;
        }
    }

    public final void c() {
        AbstractC4768sc0.b(((R50) this.H).e().f, 1);
        this.F.j(I50.f, false);
        ((View$OnLayoutChangeListenerC4598rc0) this.G).e0();
    }

    public final List d(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((C1794b90) this.F.g(I50.f8198a)).iterator();
        while (it.hasNext()) {
            G50 g50 = (G50) it.next();
            C2636g50 g502 = g50.b;
            if (!(g502 == null || g502.c == i)) {
                arrayList.add(g50);
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public void a(int i, C2636g50[] g50Arr) {
        List d = d(i);
        ArrayList arrayList = new ArrayList(g50Arr.length);
        for (C2636g50 g50 : g50Arr) {
            int i2 = g50.c;
            int i3 = 1;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 8) {
                            throw new IllegalArgumentException(AbstractC2531fV.w("Unhandled action type:", i2));
                        }
                    }
                }
                throw new IllegalArgumentException(AbstractC2531fV.w("No view defined for :", i2));
            }
            i3 = 0;
            arrayList.add(new G50(i3, g50));
        }
        ArrayList arrayList2 = (ArrayList) d;
        arrayList2.addAll(0, arrayList);
        if (N.M09VlOh_("AutofillKeyboardAccessory")) {
            arrayList2.add(arrayList2.size(), (G50) this.F.g(I50.g));
        }
        ((C1794b90) this.F.g(I50.f8198a)).t(d);
    }
}
