package defpackage;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: f31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2460f31 implements AbstractC4581rV {
    public final /* synthetic */ C2631g31 F;

    public C2460f31(C2631g31 g31, Y21 y21) {
        this.F = g31;
    }

    @Override // defpackage.AbstractC4581rV
    public void b(float f, float f2, float f3, float f4) {
        int i;
        X21 d = this.F.d();
        long uptimeMillis = SystemClock.uptimeMillis();
        d.l(false);
        float f5 = LocalizationUtils.isLayoutRtl() ? -f3 : f3;
        if (!d.A) {
            if (!d.v.d()) {
                AZ0 az0 = d.v;
                d.D = null;
                az0.c(true);
                i = az0.d.f - d.w;
            } else {
                i = 0;
            }
            d.v.b(d.w, 0, (int) f5, 0, (int) d.x, 0, 0, 0, 0, 0, uptimeMillis);
            C5950zZ0 zz0 = d.v.d;
            zz0.f += i;
            zz0.n = false;
            ((D70) d.b).s(null);
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void g(float f, float f2, float f3, float f4, boolean z) {
    }

    @Override // defpackage.AbstractC4581rV
    public void l() {
        TabModel tabModel;
        if (this.F.P.g()) {
            C2631g31 g31 = this.F;
            if (g31.G != null) {
                g31.d().g();
                C2631g31 g312 = this.F;
                if (g312.P.l) {
                    AbstractC0124Ca1 ca1 = g312.G;
                    ca1.e(!((AbstractC0246Ea1) ca1).r());
                    return;
                }
                return;
            }
        }
        X21 d = this.F.d();
        SystemClock.uptimeMillis();
        C0053Aw aw = d.E;
        if (aw != null) {
            aw.g();
        }
        d.E = null;
        if (d.A) {
            d.C = 0;
            d.z = 0;
            d.B = 0.0f;
            d.A = false;
            d.g();
            C4316pw pwVar = ((D70) d.b).h0;
            C3998o31 o31 = d.D;
            C5677xw d2 = C5677xw.d(pwVar, o31, C3998o31.f10529a, o31.s, 0.0f, 125);
            d.n = d2;
            d2.start();
            ((D70) d.b).s(null);
        }
        d.D = null;
        d.z = 0;
        if (d.o.g() && (tabModel = d.d) != null) {
            if (!tabModel.a()) {
                d.d.d();
            }
            d.e.e();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0077  */
    @Override // defpackage.AbstractC4581rV
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void n(float r11, float r12, boolean r13, int r14) {
        /*
        // Method dump skipped, instructions count: 148
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2460f31.n(float, float, boolean, int):void");
    }

    @Override // defpackage.AbstractC4581rV
    public void o(float f, float f2, boolean z, int i) {
        boolean z2;
        long uptimeMillis = SystemClock.uptimeMillis();
        C0053Aw aw = this.F.P;
        boolean z3 = true;
        if (aw.a(f, f2)) {
            aw.k = false;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            this.F.P.d.a(uptimeMillis);
            return;
        }
        X21 d = this.F.d();
        long uptimeMillis2 = SystemClock.uptimeMillis();
        d.l(false);
        C0053Aw aw2 = d.o;
        if (aw2.a(f, f2)) {
            aw2.k = false;
        } else {
            z3 = false;
        }
        if (z3) {
            d.o.d.a(uptimeMillis2);
            return;
        }
        C3998o31 i2 = d.i(f);
        if (i2 != null && !i2.l) {
            if (i2.f(f, f2) || (z && (i & 4) != 0)) {
                i2.j.d.a(uptimeMillis2);
            } else {
                i2.c(uptimeMillis2);
            }
        }
    }

    @Override // defpackage.AbstractC4581rV
    public void p(float f, float f2) {
        X21 d = this.F.d();
        long uptimeMillis = SystemClock.uptimeMillis();
        C3998o31 i = d.i(f);
        if (i == null || !i.f(f, f2)) {
            d.l(false);
            d.q(uptimeMillis, f);
            return;
        }
        i.j.k = false;
        ((CompositorViewHolder) d.c).C();
        d.d.x(AbstractC1160Ta1.e(d.d, i.d), 3);
        View b = AbstractC1160Ta1.c(d.d).b();
        d.u.setAnchorView(b);
        d.u.setVerticalOffset((-(b.getHeight() - ((int) d.O.getResources().getDimension(R.dimen.f25730_resource_name_obfuscated_RES_2131166192)))) - ((ViewGroup.MarginLayoutParams) b.getLayoutParams()).topMargin);
        d.u.setHorizontalOffset(Math.max((Math.round((i.u + i.w) * d.O.getResources().getDisplayMetrics().density) - d.u.getWidth()) - ((ViewGroup.MarginLayoutParams) b.getLayoutParams()).leftMargin, 0));
        d.u.show();
    }

    @Override // defpackage.AbstractC4581rV
    public void r(float f, float f2, float f3, float f4, float f5, float f6) {
        int i;
        this.F.P.e(f, f2);
        X21 d = this.F.d();
        long uptimeMillis = SystemClock.uptimeMillis();
        d.l(false);
        float f7 = LocalizationUtils.isLayoutRtl() ? -f3 : f3;
        d.o.e(f, f2);
        C0053Aw aw = d.E;
        if (aw != null && !aw.e(f, f2)) {
            d.E = null;
        }
        if (d.A) {
            float f8 = f - d.B;
            if (Math.abs(f8) >= 1.0f) {
                if (!LocalizationUtils.isLayoutRtl()) {
                    if (f7 >= 1.0f) {
                        d.z |= 2;
                    } else if (f7 <= -1.0f) {
                        d.z |= 1;
                    }
                } else if (f7 >= 1.0f) {
                    d.z |= 1;
                } else if (f7 <= -1.0f) {
                    d.z |= 2;
                }
                d.B = f;
                d.v(f8);
            }
        } else if (!d.v.d()) {
            C5950zZ0 zz0 = d.v.d;
            zz0.f = (int) (((float) zz0.f) + f7);
            zz0.n = false;
        } else {
            float b = d.M ? d.b(d.D, true, true, true) : 0.0f;
            if (d.D == null || b == 0.0f) {
                d.x((int) (((float) d.w) + f7));
            } else if ((i > 0 && f7 > 0.0f) || (b < 0.0f && f7 < 0.0f)) {
                d.v.e(d.w, 0, (int) b, 0, uptimeMillis, 250);
            }
        }
        if (!d.A) {
            float abs = Math.abs(f5);
            float abs2 = Math.abs(f6);
            float f9 = d.t;
            if (f6 > f9 && abs < f9 * 2.0f && abs > 0.001f && abs2 / abs > X21.f9189a) {
                d.q(uptimeMillis, f - f5);
            }
        }
        if (!d.A) {
            d.D = null;
        }
        ((D70) d.b).s(null);
    }
}
