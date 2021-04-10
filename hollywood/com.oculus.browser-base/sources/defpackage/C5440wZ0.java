package defpackage;

import android.graphics.RectF;
import android.os.SystemClock;
import java.util.Objects;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: wZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5440wZ0 implements AbstractC4581rV {
    public final /* synthetic */ AbstractC5780yZ0 F;

    public C5440wZ0(AbstractC5780yZ0 yz0, C3907nZ0 nz0) {
        this.F = yz0;
    }

    public final void a(long j) {
        AbstractC5780yZ0 yz0 = this.F;
        if (!((C3565lZ0) yz0).O0) {
            int i0 = yz0.i0();
            AbstractC5780yZ0 yz02 = this.F;
            if (!yz02.f0) {
                float f = (float) i0;
                if (Math.abs(yz02.g0 + f) > 0.4f) {
                    AbstractC5780yZ0 yz03 = this.F;
                    int i = f + yz03.g0 < 0.0f ? i0 + 1 : i0 - 1;
                    if (((AbstractC2882hZ0) yz03.a0.get(i)).F()) {
                        this.F.s0(i);
                    }
                }
            }
            AbstractC5780yZ0 yz04 = this.F;
            yz04.f0 = false;
            yz04.d0();
            AbstractC5780yZ0 yz05 = this.F;
            ((AbstractC2882hZ0) yz05.a0.get(yz05.i0())).K(j);
            this.F.m0 = 0;
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void b(float f, float f2, float f3, float f4) {
        float f5;
        float f6 = f3;
        if (!((C3565lZ0) this.F).O0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            AbstractC5780yZ0 yz0 = this.F;
            if (yz0.m0 == 0) {
                yz0.m0 = yz0.c0(uptimeMillis, f, f2, f6 * 0.033333335f, f4 * 0.033333335f);
            }
            AbstractC5780yZ0 yz02 = this.F;
            int i = yz02.m0;
            if (i == 1) {
                AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) yz02.a0.get(yz02.i0());
                if (hz0.n != 1 && hz0.v != null) {
                    if (hz0.y != 1) {
                        f6 = f4;
                    }
                    float s = hz0.s() * 0.4f;
                    hz0.v.r += AbstractC4089od0.b(f6 * 0.022222223f, -s, s);
                } else if (hz0.z == 10 && hz0.g.d() && hz0.h == 0.0f && hz0.D(f, f2, 0.0f) >= 0) {
                    if (hz0.y == 1) {
                        f6 = f4;
                    } else if (LocalizationUtils.isLayoutRtl()) {
                        f6 = -f6;
                    }
                    hz0.g.b(0, (int) hz0.q, 0, (int) f6, 0, 0, (int) hz0.v(false), (int) hz0.t(false), 0, (int) ((f6 > 0.0f ? hz0.k : hz0.l) * 0.5f), uptimeMillis);
                    hz0.P((float) hz0.g.e.f, false);
                }
            } else {
                float f7 = f;
                if (i == 2) {
                    if (yz02.o0()) {
                        f6 = f4;
                    }
                    if (this.F.o0()) {
                        f7 = f2;
                    }
                    if (this.F.o0()) {
                        f5 = this.F.G;
                    } else {
                        f5 = this.F.F;
                    }
                    AbstractC5780yZ0.X(this.F, AbstractC4089od0.b((f6 * 0.033333335f) + f7, 0.0f, f5) - f7);
                }
            }
            this.F.M();
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void g(float f, float f2, float f3, float f4, boolean z) {
        AbstractC5780yZ0 yz0 = this.F;
        if (!((C3565lZ0) yz0).O0) {
            ((AbstractC2882hZ0) yz0.a0.get(yz0.i0())).J(SystemClock.uptimeMillis(), f, f2, f3, f4, z);
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void l() {
        a(SystemClock.uptimeMillis());
    }

    @Override // defpackage.AbstractC4581rV
    public void n(float f, float f2, boolean z, int i) {
        long uptimeMillis = SystemClock.uptimeMillis();
        AbstractC5780yZ0 yz0 = this.F;
        yz0.l0 = 0;
        yz0.n0 = f;
        yz0.o0 = f2;
        Objects.requireNonNull(yz0);
        AbstractC5780yZ0 yz02 = this.F;
        if (!((C3565lZ0) yz02).O0) {
            ((AbstractC2882hZ0) yz02.a0.get(yz02.i0())).H(uptimeMillis);
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void o(float f, float f2, boolean z, int i) {
        int D;
        AbstractC5780yZ0 yz0 = this.F;
        if (!((C3565lZ0) yz0).O0) {
            yz0.f0 = true;
            int i2 = yz0.l0().i(f, f2);
            if (i2 == 0) {
                AbstractC5780yZ0 yz02 = this.F;
                AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) yz02.a0.get(yz02.i0());
                long uptimeMillis = SystemClock.uptimeMillis();
                int i3 = hz0.z;
                if ((i3 == 10 || i3 == 5 || i3 == 7 || i3 == 6) && (D = hz0.D(f, f2, hz0.f10080J)) >= 0) {
                    J70 j70 = hz0.f[D].C;
                    RectF rectF = null;
                    if (j70.h(J70.R) && j70.h(J70.E) && j70.e(J70.w) >= 0.5f) {
                        RH0 rh0 = J70.v;
                        if (j70.e(rh0) >= 0.5f && j70.e(rh0) == 1.0f && Math.abs(j70.e(J70.h)) <= 1.0f && Math.abs(j70.e(J70.i)) <= 1.0f) {
                            RectF rectF2 = (RectF) j70.g(J70.P);
                            rectF2.set(0.0f, 0.0f, 36.0f, 36.0f);
                            if (j70.h(J70.N)) {
                                rectF2.offset(j70.p() - rectF2.width(), 0.0f);
                            }
                            if (rectF2.bottom <= j70.o() && rectF2.right <= j70.p()) {
                                rectF2.offset(j70.e(J70.p) + j70.e(J70.l), j70.e(J70.q) + j70.e(J70.m));
                                float f3 = -hz0.f10080J;
                                rectF2.inset(f3, f3);
                                rectF = rectF2;
                            }
                        }
                    }
                    if (rectF != null ? rectF.contains(f, f2) : false) {
                        IZ0 iz0 = hz0.f[D];
                        float t = iz0.C.t();
                        iz0.t = hz0.H / 2.0f;
                        float f4 = 18.0f;
                        if (hz0.E()) {
                            f4 = t - 18.0f;
                        }
                        iz0.s = f4;
                        iz0.u = true;
                        hz0.E.x0(uptimeMillis, iz0.a());
                        AbstractC3535lK0.a("MobileStackViewCloseTab");
                        AbstractC3535lK0.a("MobileTabClosed");
                    } else {
                        hz0.E.J(uptimeMillis, hz0.f[D].a());
                    }
                }
            } else {
                int i0 = this.F.i0() + i2;
                if (i0 >= 0 && i0 < this.F.a0.size() && ((AbstractC2882hZ0) this.F.a0.get(i0)).F()) {
                    this.F.e0(i0);
                } else {
                    return;
                }
            }
            this.F.M();
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void p(float f, float f2) {
        AbstractC5780yZ0 yz0 = this.F;
        if (!((C3565lZ0) yz0).O0) {
            ((AbstractC2882hZ0) yz0.a0.get(yz0.i0())).I(SystemClock.uptimeMillis(), f, f2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:129:0x022f  */
    @Override // defpackage.AbstractC4581rV
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void r(float r20, float r21, float r22, float r23, float r24, float r25) {
        /*
        // Method dump skipped, instructions count: 599
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5440wZ0.r(float, float, float, float, float, float):void");
    }
}
