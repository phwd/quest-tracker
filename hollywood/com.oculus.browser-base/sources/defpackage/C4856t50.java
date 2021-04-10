package defpackage;

import java.util.HashSet;
import java.util.Set;

/* renamed from: t50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4856t50 extends AbstractC2477f90 implements AbstractC1641aI0 {
    public final Set F = new HashSet();
    public final Set G = new HashSet();
    public final UH0 H;
    public final AbstractC1953c50 I;

    public C4856t50(UH0 uh0, AbstractC1953c50 c50) {
        this.H = uh0;
        this.I = c50;
    }

    @Override // defpackage.AbstractC2477f90, defpackage.AbstractC2648g90
    public void a(AbstractC2819h90 h90, int i, int i2) {
    }

    @Override // defpackage.AbstractC1641aI0
    public void b(AbstractC1821bI0 bi0, Object obj) {
        int i;
        KH0 kh0 = (KH0) obj;
        QH0 qh0 = I50.b;
        if (kh0 == qh0) {
            if (this.H.h(qh0)) {
                int i2 = 0;
                if (this.F.isEmpty()) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= 5) {
                            i = 0;
                            break;
                        } else if (g(i3)) {
                            i = 1;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    c(i);
                }
                c(4);
                c(2);
                if (this.H.h(I50.b)) {
                    while (true) {
                        UH0 uh0 = this.H;
                        OH0 oh0 = I50.f8198a;
                        if (i2 < ((C1794b90) uh0.g(oh0)).size()) {
                            C2636g50 g50 = ((G50) ((C1794b90) this.H.g(oh0)).get(i2)).b;
                            if (g50 != null) {
                                c(g50.c == 2 ? 4 : 3);
                            }
                            i2++;
                        } else {
                            return;
                        }
                    }
                }
            } else {
                this.F.clear();
                this.G.clear();
            }
        } else if (kh0 != I50.d && kh0 != I50.f && kh0 != I50.e && kh0 != I50.h && kh0 != I50.c && kh0 != I50.i && kh0 != I50.k) {
            TH0 th0 = I50.j;
        }
    }

    public final void c(int i) {
        if (g(i)) {
            this.F.add(Integer.valueOf(i));
            AbstractC3364kK0.g("KeyboardAccessory.AccessoryBarShown", i, 5);
        }
    }

    public final void d(AbstractC2819h90 h90, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            C2636g50 g50 = ((G50) ((C1794b90) this.H.g(I50.f8198a)).get(i3)).b;
            if (g50 != null) {
                c(g50.c == 2 ? 4 : 3);
                if (this.G.add(Integer.valueOf(g50.c))) {
                    AbstractC3364kK0.g("KeyboardAccessory.AccessoryActionImpression", g50.c, 8);
                }
            }
        }
    }

    @Override // defpackage.AbstractC2477f90, defpackage.AbstractC2648g90
    public void f(AbstractC2819h90 h90, int i, int i2) {
        d(h90, i, i2);
    }

    public final boolean g(int i) {
        if (!this.H.h(I50.b) || this.F.contains(Integer.valueOf(i))) {
            return false;
        }
        if (i == 0 || i == 1) {
            return true;
        }
        if (i == 2) {
            return ((R50) this.I).g();
        }
        if (i == 3) {
            return AbstractC5026u50.a((C1794b90) this.H.g(I50.f8198a), new int[]{1, 0});
        }
        if (i != 4) {
            return false;
        }
        return AbstractC5026u50.a((C1794b90) this.H.g(I50.f8198a), new int[]{2});
    }

    @Override // defpackage.AbstractC2648g90
    public void l(AbstractC2819h90 h90, int i, int i2, Object obj) {
        Void r6 = (Void) obj;
        for (int i3 = i; i3 < i + i2; i3++) {
            C2636g50 g50 = ((G50) ((C1794b90) this.H.g(I50.f8198a)).get(i3)).b;
            if (g50 != null) {
                this.G.remove(Integer.valueOf(g50.c));
            }
        }
        d(h90, i, i2);
    }
}
