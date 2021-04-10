package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.browser.R;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: yt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5839yt0 extends AbstractC2882hZ0 {
    public float M;
    public int N = -1;
    public int O = -1;
    public float P;
    public float Q;
    public float R = 1.0f;
    public float S = 1.0f;
    public float T;

    public C5839yt0(Context context, AbstractC5780yZ0 yz0) {
        super(context, yz0);
    }

    @Override // defpackage.AbstractC2882hZ0
    public float A() {
        return -0.8f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void I(long j, float f, float f2) {
        int D;
        if (this.z == 10 && (D = D(f, f2, 0.0f)) >= 0) {
            V(j, 3, D, false);
            this.R = 0.0f;
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public void J(long j, float f, float f2, float f3, float f4, boolean z) {
        float f5;
        float f6;
        int i = this.z;
        if ((i == 8 || i == 10) && this.f != null) {
            if (this.N < 0) {
                V(j, 8, -1, false);
            }
            boolean z2 = this.y != 1 ? !(!LocalizationUtils.isLayoutRtl() ? f <= f3 : f > f3) : f2 > f4;
            float f7 = z2 ? f3 : f;
            float f8 = z2 ? f4 : f2;
            float f9 = z2 ? f : f3;
            float f10 = z2 ? f2 : f4;
            if (this.y == 1) {
                f5 = f8;
            } else {
                f5 = LocalizationUtils.isLayoutRtl() ? -f7 : f7;
            }
            if (this.y == 1) {
                f6 = f10;
            } else {
                f6 = LocalizationUtils.isLayoutRtl() ? -f9 : f9;
            }
            int i2 = -1;
            if (z) {
                this.N = -1;
                this.O = -1;
                this.u = null;
                e(j, false);
            }
            int i3 = this.N;
            int i4 = this.O;
            if (i3 < 0) {
                int C = C(f7, f8);
                int C2 = C(f9, f10);
                if (C < 0 || C2 < 0) {
                    i4 = -1;
                } else {
                    i4 = C2;
                    i2 = C;
                }
            } else {
                i2 = i3;
            }
            if (i2 >= 0 && this.N == i2 && this.O == i4) {
                float b = AbstractC4089od0.b(this.q, v(false), t(false));
                if (i2 >= i4) {
                    float f11 = f5 - this.P;
                    if (i2 == 0) {
                        P(b + f11, false);
                    } else {
                        P(N(O(this.f[i2].k + b) + f11) - this.f[i2].k, false);
                    }
                } else {
                    float b2 = b(this.f[i2], b);
                    float f12 = (f5 - this.P) + b2;
                    float b3 = b(this.f[i4], b);
                    float f13 = (f6 - this.Q) + b3;
                    P((N((f12 + b2) / 2.0f) + b) - N(b2), true);
                    float f14 = f12;
                    float f15 = f14;
                    int i5 = i2;
                    while (i5 <= i4) {
                        float b4 = b3 == b2 ? 1.0f : (b(this.f[i5], b) - b2) / (b3 - b2);
                        float min = Math.min(f15, Math.max(f14, (b4 * f13) + ((1.0f - b4) * f12)));
                        float f16 = IZ0.f8234a + min;
                        float b5 = this.f[i5].b(this.y) + min;
                        this.f[i5].k = N(min) - this.q;
                        i5++;
                        f14 = f16;
                        f15 = b5;
                    }
                    float f17 = f13 - b3;
                    int i6 = i4 + 1;
                    while (true) {
                        IZ0[] iz0Arr = this.f;
                        if (i6 >= iz0Arr.length) {
                            break;
                        }
                        f17 /= 2.0f;
                        float min2 = Math.min(f15, Math.max(f14, b(iz0Arr[i6], b) + f17));
                        f14 = IZ0.f8234a + min2;
                        f15 = this.f[i6].b(this.y) + min2;
                        this.f[i6].k = N(min2) - this.q;
                        i6++;
                    }
                    float f18 = f12 - b2;
                    for (int i7 = i2 - 1; i7 > 0; i7--) {
                        f18 /= 2.0f;
                        this.f[i7].k = N(Math.min(f12 - IZ0.f8234a, Math.max(f12 - this.f[i7].b(this.y), b(this.f[i7], b) + f18))) - this.q;
                    }
                }
            }
            this.N = i2;
            this.O = i4;
            this.P = f5;
            this.Q = f6;
            this.R = 0.0f;
            this.E.M();
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public void K(long j) {
        if (this.N >= 0) {
            V(j, 4, -1, false);
            this.E.M();
        }
        e(j, true);
        M();
        T(j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    @Override // defpackage.AbstractC2882hZ0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void L() {
        /*
        // Method dump skipped, instructions count: 127
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5839yt0.L():void");
    }

    @Override // defpackage.AbstractC2882hZ0
    public void M() {
        super.M();
        this.N = -1;
        this.O = -1;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float N(float f) {
        return a0(f, this.M);
    }

    @Override // defpackage.AbstractC2882hZ0
    public float O(float f) {
        return b0(f, this.M);
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean Q() {
        return true;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean R() {
        return true;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean S() {
        return true;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void T(long j) {
        if (this.g.d()) {
            int v = (int) v(false);
            int t = (int) t(false);
            float f = this.q;
            float f2 = (float) v;
            if (f < f2 || f > ((float) t)) {
                AZ0 az0 = this.g;
                az0.c = 1;
                az0.d.l(0, 0, 0, j);
                az0.e.l((int) f, v, t, j);
                P(AbstractC4089od0.b(this.q, f2, (float) t), false);
                this.E.M();
            }
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public void Y(int i) {
        IZ0[] iz0Arr = this.f;
        this.M = x() * 0.4f;
        super.Y(i);
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean a() {
        int i = this.z;
        return (i == 10 || i == 3 || i == 0) && this.N < 0;
    }

    public final float a0(float f, float f2) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        return f >= f2 ? f + f2 : ((float) Math.sqrt((double) (f * f2))) * 2.0f;
    }

    public final float b0(float f, float f2) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        float f3 = 2.0f * f2;
        if (f >= f3) {
            return f - f2;
        }
        float f4 = ((f - f2) / f3) + 0.5f;
        return f4 * f4 * f2;
    }

    @Override // defpackage.AbstractC2882hZ0
    public int h() {
        AbstractC5780yZ0 yz0 = this.E;
        int C = C(yz0.F / 2.0f, yz0.G / 2.0f);
        float f = this.t;
        if (f > 0.0f) {
            C++;
        }
        if (f < 0.0f) {
            C--;
        }
        return AbstractC4089od0.c(C, 0, this.f.length - 1);
    }

    @Override // defpackage.AbstractC2882hZ0
    public int i(int i) {
        int i2 = 0;
        if (i <= 1) {
            return 0;
        }
        float x = x();
        int max = (int) Math.max(0.26f * x, this.T);
        if (this.f != null) {
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i2 >= iz0Arr.length) {
                    break;
                }
                if (!iz0Arr[i2].w) {
                    max = (int) Math.min((float) max, iz0Arr[i2].b(this.y));
                }
                i2++;
            }
        }
        return Math.max((int) ((x - 20.0f) / (((float) i) * 0.8f)), max);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002b  */
    @Override // defpackage.AbstractC2882hZ0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j() {
        /*
        // Method dump skipped, instructions count: 380
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5839yt0.j():void");
    }

    @Override // defpackage.AbstractC2882hZ0
    public void k(Context context) {
        super.k(context);
        Resources resources = context.getResources();
        float f = 1.0f / resources.getDisplayMetrics().density;
        this.S = 1.0f / (resources.getDimension(R.dimen.f19040_resource_name_obfuscated_RES_2131165523) * f);
        this.T = ((float) resources.getDimensionPixelOffset(R.dimen.f20850_resource_name_obfuscated_RES_2131165704)) * f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean m(float f, boolean z) {
        int i = 0;
        if (this.f == null || this.z != 10 || this.R >= 1.0f || f == 0.0f) {
            return false;
        }
        float min = Math.min(Math.abs(f) * this.S, 1.0f - this.R);
        float f2 = min / (1.0f - this.R);
        float x = x();
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            IZ0[] iz0Arr = this.f;
            if (i >= iz0Arr.length) {
                break;
            }
            float f3 = iz0Arr[i].k;
            float N2 = N((float) (this.e * i));
            float min2 = Math.min(x, O(this.q + f3));
            float min3 = Math.min(x, O(this.q + N2));
            if (min2 == min3) {
                this.f[i].k = N2;
            } else {
                float a2 = AbstractC2531fV.a(N2, f3, f2, f3);
                if (min2 == Math.min(x, O(this.q + a2))) {
                    this.f[i].k = a2;
                } else if ((min3 - min2) * f > 0.0f || z) {
                    this.f[i].k = a2;
                    z3 = true;
                } else {
                    z2 = true;
                }
            }
            i++;
        }
        if (!z2) {
            this.R += min;
        }
        return z3;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float u() {
        if (!AbstractC4772sd1.a()) {
            return this.E.g0();
        }
        AbstractC5780yZ0 yz0 = this.E;
        return yz0.G - yz0.k0();
    }

    @Override // defpackage.AbstractC2882hZ0
    public float v(boolean z) {
        float f;
        float f2 = 0.0f;
        if (this.f != null) {
            int i = 0;
            f = 0.0f;
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i >= iz0Arr.length) {
                    break;
                }
                if (!iz0Arr[i].w && iz0Arr[i].C.F()) {
                    f = Math.max(this.f[i].k, f);
                }
                i++;
            }
        } else {
            f = 0.0f;
        }
        if (z) {
            f2 = -this.l;
        }
        return f2 - f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float w() {
        return 0.9f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float y() {
        return -0.7f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float z() {
        return -0.5f;
    }
}
