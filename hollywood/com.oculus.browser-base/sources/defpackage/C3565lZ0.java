package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import java.util.ArrayList;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: lZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3565lZ0 extends AbstractC5780yZ0 {
    public boolean N0;
    public boolean O0;

    public C3565lZ0(Context context, K70 k70, F70 f70, AbstractC0956Pq0 pq0) {
        super(context, k70, f70, pq0);
    }

    @Override // defpackage.AbstractC2300e70
    public void C(long j, int i) {
        AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) this.a0.get(j0(i));
        if (hz0 != null) {
            hz0.X(j, i);
        }
        t0(true);
        if (!((AbstractC2882hZ0) this.a0.get(1)).F()) {
            G(false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    @Override // defpackage.AbstractC2300e70
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void E(long r13, int r15, int r16, int r17, boolean r18, boolean r19, float r20, float r21) {
        /*
            r12 = this;
            r0 = r12
            r1 = r15
            r2 = 1
            r0.Z = r2
            r3 = 0
            r12.Q(r15, r3)
            java.util.ArrayList r4 = r0.a0
            int r5 = r12.j0(r15)
            java.lang.Object r4 = r4.get(r5)
            r5 = r4
            hZ0 r5 = (defpackage.AbstractC2882hZ0) r5
            N81 r4 = r5.c
            org.chromium.chrome.browser.tab.Tab r4 = defpackage.AbstractC1160Ta1.d(r4, r15)
            if (r4 != 0) goto L_0x001f
            goto L_0x0031
        L_0x001f:
            IZ0[] r4 = r5.f
            if (r4 == 0) goto L_0x0036
            int r4 = r4.length
            r6 = r3
        L_0x0025:
            if (r6 >= r4) goto L_0x0036
            IZ0[] r7 = r5.f
            r7 = r7[r6]
            int r7 = r7.a()
            if (r7 != r1) goto L_0x0033
        L_0x0031:
            r2 = r3
            goto L_0x0039
        L_0x0033:
            int r6 = r6 + 1
            goto L_0x0025
        L_0x0036:
            r5.l(r2)
        L_0x0039:
            if (r2 != 0) goto L_0x003c
            goto L_0x004e
        L_0x003c:
            r5.d = r3
            r6 = r13
            r5.n(r13)
            r8 = 1
            N81 r2 = r5.c
            int r9 = defpackage.AbstractC1160Ta1.e(r2, r15)
            r10 = -1
            r11 = 0
            r5.U(r6, r8, r9, r10, r11)
        L_0x004e:
            r12.t0(r3)
            r1 = r18
            r12.G(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3565lZ0.E(long, int, int, int, boolean, boolean, float, float):void");
    }

    @Override // defpackage.AbstractC2300e70
    public void G(boolean z) {
        float f;
        float f2;
        if (m0()) {
            this.O0 = true;
            M();
            C5147up0 up0 = (C5147up0) this.a0.get(!z ? 1 : 0);
            boolean z2 = !z;
            if (up0.f == null || up0.N) {
                up0.N = true;
                up0.E.q0();
                return;
            }
            up0.N = true;
            up0.M = true;
            int i = 0;
            while (true) {
                IZ0[] iz0Arr = up0.f;
                if (i >= iz0Arr.length) {
                    break;
                }
                iz0Arr[i].r = 0.0f;
                i++;
            }
            up0.p();
            C4316pw m = up0.E.m();
            ArrayList arrayList = new ArrayList();
            int a0 = up0.a0();
            for (int i2 = a0 - 1; i2 <= a0 + 1; i2++) {
                if (i2 >= 0) {
                    IZ0[] iz0Arr2 = up0.f;
                    if (i2 < iz0Arr2.length) {
                        IZ0 iz0 = iz0Arr2[i2];
                        if (!z2) {
                            f = ((float) up0.e) * -2.5f;
                            f2 = iz0.k;
                        } else {
                            f = ((float) up0.e) * 2.5f;
                            f2 = iz0.k;
                        }
                        arrayList.add(C5677xw.d(m, iz0, IZ0.d, iz0.k, f + f2, 250));
                    }
                }
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(new C4807sp0(up0));
            animatorSet.start();
            return;
        }
        e0(z ? 1 : 0);
        this.N0 = true;
    }

    @Override // defpackage.AbstractC2300e70
    public void K(long j, boolean z) {
        boolean z2;
        AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) this.a0.get(z ? 1 : 0);
        if (hz0.f != null) {
            int i = 0;
            z2 = false;
            while (true) {
                IZ0[] iz0Arr = hz0.f;
                if (i >= iz0Arr.length) {
                    break;
                }
                z2 |= !iz0Arr[i].w;
                iz0Arr[i].w = true;
                i++;
            }
        } else {
            z2 = true;
        }
        if (z2) {
            hz0.s = hz0.r;
            hz0.e = hz0.i(0);
            if (hz0.f != null) {
                int i2 = 0;
                while (true) {
                    IZ0[] iz0Arr2 = hz0.f;
                    if (i2 >= iz0Arr2.length) {
                        break;
                    }
                    IZ0 iz0 = iz0Arr2[i2];
                    float f = 0.0f;
                    iz0.t = 0.0f;
                    if (hz0.E()) {
                        f = iz0.C.t();
                    }
                    iz0.s = f;
                    iz0.u = true;
                    i2++;
                }
            }
            hz0.V(j, 6, -1, false);
        }
        hz0.d = true;
        t0(true);
        if (!((AbstractC2882hZ0) this.a0.get(1)).F()) {
            G(false);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void O(AbstractC0124Ca1 ca1, TabContentManager tabContentManager) {
        this.L = ca1;
        N(tabContentManager);
        Q91 q91 = this.K0;
        if (q91 != null) {
            q91.H = ca1;
        }
        r0();
        new C4590rZ0(this, this.L);
        if (((AbstractC0246Ea1) ca1).c.d() == null) {
            ((AbstractC0246Ea1) this.L).c(new C3394kZ0(this));
            return;
        }
        y0();
    }

    @Override // defpackage.AbstractC5780yZ0
    public int c0(long j, float f, float f2, float f3, float f4) {
        if (m0()) {
            return 1;
        }
        if (this.a0.size() == 2 && !((AbstractC2882hZ0) this.a0.get(1)).F()) {
            return 1;
        }
        boolean z = false;
        if (this.a0.size() == 0) {
            return 0;
        }
        if (this.a0.size() == 1) {
            return 1;
        }
        int i0 = i0();
        if (this.l0 == 0) {
            if (Math.abs(f3) > Math.abs(f4)) {
                this.l0 = 1;
            } else {
                this.l0 = 2;
            }
        }
        if ((this.l0 == 2) ^ o0()) {
            return 1;
        }
        float f5 = this.n0 - (f + f3);
        float f6 = this.o0 - (f2 + f4);
        if (o0()) {
            f5 = f6;
        }
        boolean z2 = !o0() && LocalizationUtils.isLayoutRtl();
        boolean z3 = (i0 == 0 && !z2) || (i0 == this.a0.size() - 1 && z2);
        if ((i0 == 0 && z2) || (i0 == this.a0.size() - 1 && !z2)) {
            z = true;
        }
        if (z3 && f5 < 0.0f) {
            return 1;
        }
        if (!z || f5 <= 0.0f) {
            return 2;
        }
        return 1;
    }

    @Override // defpackage.AbstractC5780yZ0
    public int h0() {
        if (!m0() && !((AbstractC2882hZ0) this.a0.get(1)).F() && !this.N0) {
            return 0;
        }
        return -1;
    }

    @Override // defpackage.AbstractC5780yZ0
    public int j0(int i) {
        if (i != -1) {
            return AbstractC1160Ta1.d(((AbstractC0246Ea1) this.L).l(true), i) != null ? 1 : 0;
        }
        int i2 = this.A0;
        if (i2 != -1) {
            return i2;
        }
        return ((AbstractC0246Ea1) this.L).r() ? 1 : 0;
    }

    @Override // defpackage.AbstractC5780yZ0
    public void p0() {
        boolean z = false;
        this.N0 = false;
        int i = this.A0;
        if (i != -1) {
            AbstractC0124Ca1 ca1 = this.L;
            if (i == 1) {
                z = true;
            }
            ca1.e(z);
            this.A0 = -1;
        }
    }

    @Override // defpackage.AbstractC5780yZ0
    public void q0() {
        float f;
        float f2;
        int j0 = j0(-1);
        this.g0 = (float) (-j0);
        C5147up0 up0 = (C5147up0) this.a0.get(j0);
        boolean z = j0 != 1;
        if (up0.f == null || !up0.N) {
            up0.N = false;
            ((C3565lZ0) up0.E).O0 = false;
            return;
        }
        up0.N = false;
        up0.M = true;
        C4316pw m = up0.E.m();
        ArrayList arrayList = new ArrayList();
        int a0 = up0.a0();
        for (int i = a0 - 1; i <= a0 + 1; i++) {
            if (i >= 0) {
                IZ0[] iz0Arr = up0.f;
                if (i < iz0Arr.length) {
                    IZ0 iz0 = iz0Arr[i];
                    if (!z) {
                        f = ((float) up0.e) * 2.5f;
                        f2 = iz0.k;
                    } else {
                        f = ((float) up0.e) * -2.5f;
                        f2 = iz0.k;
                    }
                    arrayList.add(C5677xw.d(m, iz0, IZ0.d, f + f2, (float) (up0.e * i), 250));
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(new C4977tp0(up0));
        animatorSet.start();
    }

    @Override // defpackage.AbstractC5780yZ0
    public void s0(int i) {
        if (i != j0(-1)) {
            if (i == 0) {
                AbstractC3535lK0.a("MobileStackViewNormalMode");
            } else {
                AbstractC3535lK0.a("MobileStackViewIncognitoMode");
            }
        }
        this.A0 = i;
    }

    @Override // defpackage.AbstractC5780yZ0
    public void x0(long j, int i) {
        ((AbstractC2882hZ0) this.a0.get(j0(i))).X(j, i);
        int count = ((AbstractC0246Ea1) this.L).l(true).getCount();
        TabModel m = ((AbstractC0246Ea1) this.L).m(i);
        if (m != null && m.a()) {
            count--;
        }
        boolean z = count > 0;
        u0(true, z);
        if (!z) {
            G(false);
        }
    }

    public final void y0() {
        AbstractC2882hZ0 hz0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(((AbstractC0246Ea1) this.L).c.g(false));
        arrayList.add(((AbstractC0246Ea1) this.L).c.g(true));
        if (this.a0.size() > arrayList.size()) {
            this.a0.subList(arrayList.size(), this.a0.size()).clear();
        }
        while (this.a0.size() < arrayList.size()) {
            if (m0()) {
                hz0 = new C5147up0(this.f9833J, this);
            } else {
                hz0 = new C5839yt0(this.f9833J, this);
            }
            hz0.G(this.p0, this.q0, this.r0);
            this.a0.add(hz0);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            ((AbstractC2882hZ0) this.a0.get(i)).c = (N81) arrayList.get(i);
        }
    }
}
