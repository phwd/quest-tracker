package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: yZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5780yZ0 extends AbstractC2300e70 {
    public static final AbstractC3719mR V = new C3907nZ0("INNER_MARGIN_PERCENT");
    public static final AbstractC3719mR W = new C4078oZ0("STACK_OFFSET_Y_PERCENT");
    public static final AbstractC3719mR X = new C4249pZ0("STACK_SNAP");
    public int A0;
    public C5270vZ0 B0;
    public C5270vZ0 C0;
    public final ViewGroup D0;
    public final C4411qV E0;
    public C5440wZ0 F0;
    public final ArrayList G0;
    public final AbstractC0956Pq0 H0;
    public final AbstractC2230dk I0;
    public Callback J0;
    public Q91 K0;
    public boolean L0;
    public boolean M0;
    public boolean Y;
    public boolean Z;
    public final ArrayList a0;
    public final ArrayList b0;
    public final float c0;
    public int d0;
    public float e0;
    public boolean f0;
    public float g0;
    public float h0;
    public final int i0;
    public float j0;
    public float k0;
    public int l0 = 0;
    public int m0 = 0;
    public float n0;
    public float o0;
    public float p0;
    public float q0;
    public int r0;
    public IZ0[] s0;
    public final ArrayList t0 = new ArrayList();
    public final C5610xZ0 u0;
    public final C5100uZ0 v0;
    public Comparator w0;
    public boolean x0;
    public int y0;
    public int z0;

    public AbstractC5780yZ0(Context context, K70 k70, F70 f70, AbstractC0956Pq0 pq0) {
        super(context, k70, f70);
        C5610xZ0 xz0 = new C5610xZ0(null);
        this.u0 = xz0;
        this.v0 = new C5100uZ0(null);
        this.w0 = xz0;
        this.A0 = -1;
        this.G0 = new ArrayList();
        C5440wZ0 wz0 = new C5440wZ0(this, null);
        this.F0 = wz0;
        this.E0 = new C4411qV(context, wz0, true, true);
        this.i0 = 55;
        this.e0 = 1.5f;
        this.a0 = new ArrayList();
        this.b0 = new ArrayList();
        this.D0 = new FrameLayout(this.f9833J);
        this.c0 = context.getResources().getDisplayMetrics().density;
        this.H0 = pq0;
        this.I0 = new C4420qZ0(this);
        C3736mZ0 mz0 = new C3736mZ0(this);
        this.J0 = mz0;
        ((C1078Rq0) pq0).l(mz0);
    }

    public static void X(AbstractC5780yZ0 yz0, float f) {
        yz0.a0(X);
        float f02 = yz0.f0();
        float f2 = yz0.h0;
        float f3 = f / f02;
        if (!yz0.o0() && LocalizationUtils.isLayoutRtl()) {
            f3 = -f3;
        }
        float f4 = f2 + f3;
        yz0.h0 = f4;
        yz0.g0 = AbstractC4089od0.b(f4, 0.0f, (float) yz0.h0());
        yz0.M();
    }

    @Override // defpackage.AbstractC2300e70
    public void F(int i) {
        L(SystemClock.currentThreadTimeMillis(), true);
    }

    @Override // defpackage.AbstractC2300e70
    public void H(long j, int i) {
        if (this.K0 == null) {
            this.L0 = true;
        } else {
            P(j, false);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void J(long j, int i) {
        AbstractC0124Ca1 ca1 = this.L;
        if (((AbstractC0246Ea1) ca1).e != this.y0 && ((AbstractC0246Ea1) ca1).i().index() == ((AbstractC0246Ea1) this.L).i().index()) {
            AbstractC3535lK0.a("MobileTabSwitched");
        }
        b0(j);
        if (i == -1) {
            i = ((AbstractC0246Ea1) this.L).k();
        }
        super.J(j, i);
        AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) this.a0.get(i0());
        hz0.U(j, 2, AbstractC1160Ta1.e(hz0.c, i), -1, false);
        t0(false);
        v0(false);
        d0();
    }

    @Override // defpackage.AbstractC2300e70
    public boolean L(long j, boolean z) {
        boolean z2;
        boolean z3;
        if (!this.G0.isEmpty()) {
            if (z) {
                j();
                z2 = true;
            } else {
                z2 = !n0();
            }
            if (z2 || z) {
                p0();
            }
        } else {
            z2 = true;
        }
        boolean z4 = true;
        for (int i = 0; i < this.a0.size(); i++) {
            AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) this.a0.get(i);
            Animator animator = hz0.D;
            if (animator != null) {
                z3 = !animator.isRunning();
                hz0.o(j, z);
            } else {
                z3 = true;
            }
            z4 &= z3;
        }
        boolean z5 = true;
        for (int i2 = 0; i2 < this.a0.size(); i2++) {
            AbstractC2882hZ0 hz02 = (AbstractC2882hZ0) this.a0.get(i2);
            if (!z) {
                if (hz02.r != hz02.q) {
                    if (hz02.g.a(j)) {
                        float f = (float) hz02.g.e.e;
                        hz02.m(f - hz02.r, true);
                        hz02.r = f;
                    } else {
                        float f2 = hz02.r;
                        float f3 = hz02.q;
                        hz02.r = AbstractC4089od0.e(AbstractC4089od0.b(f2, f3 - 20.0f, 20.0f + f3), f3, 0.9f);
                    }
                    hz02.E.M();
                } else {
                    hz02.g.c(true);
                }
                hz02.Z();
            }
            C3053iZ0 iz0 = hz02.C;
            boolean z6 = iz0 == null || z || !iz0.b.isRunning();
            if (hz02.C != null) {
                hz02.o(j, z);
            }
            if (z) {
                hz02.p();
            }
            z5 &= z6;
        }
        if (z2 && z4 && z5) {
            return true;
        }
        if (!z2 || !z5) {
            M();
        }
        return false;
    }

    @Override // defpackage.AbstractC2300e70
    public void P(long j, boolean z) {
        this.Q = false;
        this.R = true;
        this.S = -1;
        this.M0 = false;
        if (!this.Y) {
            AbstractC3535lK0.a("MobileToolbarShowStackView");
            AbstractC0124Ca1 ca1 = this.L;
            this.y0 = ((AbstractC0246Ea1) ca1).e;
            this.z0 = ((AbstractC0246Ea1) ca1).k();
        }
        this.Y = true;
        Tab j2 = ((AbstractC0246Ea1) this.L).j();
        if (j2 != null && j2.isNativePage()) {
            this.M.b(j2);
        }
        this.D0.removeAllViews();
        int i02 = i0();
        int size = this.a0.size() - 1;
        while (size >= 0) {
            ((AbstractC2882hZ0) this.a0.get(size)).d = false;
            if (((AbstractC2882hZ0) this.a0.get(size)).F()) {
                AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) this.a0.get(size);
                hz0.K = size == i02;
                hz0.w = hz0.r();
                hz0.j = 0;
                hz0.l(false);
            } else {
                ((AbstractC2882hZ0) this.a0.get(size)).d();
            }
            size--;
        }
        this.w0 = this.v0;
        r0();
        int size2 = this.a0.size() - 1;
        while (size2 >= 0) {
            if (((AbstractC2882hZ0) this.a0.get(size2)).F()) {
                boolean z2 = size2 != i0();
                AbstractC2882hZ0 hz02 = (AbstractC2882hZ0) this.a0.get(size2);
                boolean z3 = !(!z2);
                IZ0[] iz0Arr = hz02.f;
                hz02.e = hz02.i(iz0Arr != null ? iz0Arr.length : 0);
                hz02.L();
                hz02.V(j, 0, -1, z3);
            }
            size2--;
        }
        t0(true);
        v0(true);
        e0(i0());
        if (!z) {
            L(j, true);
        }
        T(j, 0);
    }

    @Override // defpackage.AbstractC2300e70
    public void Q(int i, boolean z) {
        AbstractC0956Pq0 pq0 = this.H0;
        if (((C1078Rq0) pq0).H != null) {
            ((C1551Zj) ((AbstractC2400ek) ((C1078Rq0) pq0).H)).Y.c(this.I0);
        }
        this.N.e(i, z);
        this.Q = true;
        this.S = i;
        this.Y = false;
        if (this.z0 == i) {
            AbstractC3535lK0.a("MobileTabReturnedToCurrentTab");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r12v17 */
    @Override // defpackage.AbstractC2300e70
    public void T(long j, long j2) {
        float f;
        float f2;
        int i;
        int i2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        int i3;
        float f11;
        float f12;
        AbstractC5780yZ0 yz0 = this;
        if (yz0.a0.size() != 0) {
            if (yz0.b0.size() > yz0.a0.size()) {
                yz0.b0.subList(yz0.a0.size(), yz0.b0.size()).clear();
            }
            while (yz0.b0.size() < yz0.a0.size()) {
                yz0.b0.add(new RectF());
            }
            C5270vZ0 l02 = l0();
            boolean z = false;
            if (!yz0.b0.isEmpty()) {
                ((RectF) yz0.b0.get(0)).left = l02.e();
                ((RectF) yz0.b0.get(0)).right = l02.k() + ((RectF) yz0.b0.get(0)).left;
                ((RectF) yz0.b0.get(0)).top = l02.h();
                ((RectF) yz0.b0.get(0)).bottom = l02.c() + ((RectF) yz0.b0.get(0)).top;
            }
            boolean z2 = true;
            for (int i4 = 1; i4 < yz0.b0.size(); i4++) {
                int i5 = i4 - 1;
                ((RectF) yz0.b0.get(i4)).left = l02.f() + ((RectF) yz0.b0.get(i5)).left;
                ((RectF) yz0.b0.get(i4)).right = l02.k() + ((RectF) yz0.b0.get(i4)).left;
                ((RectF) yz0.b0.get(i4)).top = l02.g() + ((RectF) yz0.b0.get(i5)).top;
                ((RectF) yz0.b0.get(i4)).bottom = l02.c() + ((RectF) yz0.b0.get(i4)).top;
            }
            int i6 = 0;
            while (true) {
                f = 1.0f;
                f2 = 0.0f;
                if (i6 >= yz0.a0.size()) {
                    break;
                }
                float b = AbstractC4089od0.b(1.0f - Math.abs(((float) i6) + yz0.g0), 0.0f, 1.0f);
                boolean z3 = yz0.w0 == yz0.v0 && !m0();
                AbstractC2882hZ0 hz0 = (AbstractC2882hZ0) yz0.a0.get(i6);
                int index = z3 ? ((AbstractC2882hZ0) yz0.a0.get(i6)).c.index() : -1;
                if (hz0.f != null) {
                    hz0.x = index;
                    int i7 = 0;
                    while (true) {
                        IZ0[] iz0Arr = hz0.f;
                        if (i7 >= iz0Arr.length) {
                            break;
                        }
                        iz0Arr[i7].C.k(J70.w, b);
                        i7++;
                    }
                }
                i6++;
            }
            int i8 = 0;
            while (i8 < yz0.a0.size()) {
                AbstractC2882hZ0 hz02 = (AbstractC2882hZ0) yz0.a0.get(i8);
                RectF rectF = (RectF) yz0.b0.get(i8);
                IZ0[] iz0Arr2 = hz02.f;
                if (iz0Arr2 == null || iz0Arr2.length == 0) {
                    i2 = i8;
                    f3 = f;
                } else {
                    float B = hz02.B(rectF);
                    float s = hz02.s();
                    int i9 = z;
                    while (true) {
                        IZ0[] iz0Arr3 = hz02.f;
                        if (i9 >= iz0Arr3.length) {
                            break;
                        }
                        IZ0 iz0 = iz0Arr3[i9 == true ? 1 : 0];
                        J70 j70 = iz0.C;
                        float f13 = iz0.r;
                        float g = AbstractC2882hZ0.g(f13, s, iz0.u);
                        j70.k(J70.g, iz0.q * g * B);
                        j70.k(J70.x, g);
                        j70.k(J70.t, iz0.p * AbstractC2882hZ0.f(f13, s));
                        i9 = (i9 == true ? 1 : 0) + 1;
                    }
                    if (hz02.Q()) {
                        float f14 = Float.MAX_VALUE;
                        int i10 = z;
                        while (true) {
                            IZ0[] iz0Arr4 = hz02.f;
                            if (i10 >= iz0Arr4.length) {
                                break;
                            }
                            if (!iz0Arr4[i10 == true ? 1 : 0].w) {
                                float min = Math.min(f14, iz0Arr4[i10].k);
                                hz02.f[i10].k = min;
                                f14 = hz02.N(hz02.f[i10].b(hz02.y) + hz02.O(hz02.r + min)) + (-hz02.r);
                            }
                            i10 = (i10 == true ? 1 : 0) + 1;
                        }
                    }
                    boolean z4 = hz02.y == z2 ? z2 : z;
                    float width = rectF.width();
                    float height = rectF.height();
                    float f15 = hz02.h;
                    if (f15 >= f2) {
                        f4 = hz02.k;
                    } else {
                        f4 = hz02.l;
                    }
                    float f16 = f15 / f4;
                    float b2 = AbstractC4089od0.b(hz02.r, hz02.v(z), hz02.t(z));
                    float B2 = hz02.B(rectF);
                    boolean z5 = z;
                    int i11 = z5;
                    float f17 = f2;
                    int i12 = z5;
                    while (true) {
                        IZ0[] iz0Arr5 = hz02.f;
                        if (i12 >= iz0Arr5.length) {
                            break;
                        }
                        IZ0 iz02 = iz0Arr5[i12 == true ? 1 : 0];
                        J70 j702 = iz02.C;
                        float b3 = hz02.b(iz02, iz02.w ? hz02.s : b2);
                        if (hz02.S()) {
                            b3 = Math.max(f17, b3);
                            f11 = b2;
                            if (i11 < 3) {
                                i3 = i8;
                                f10 = B2;
                                f17 += IZ0.f8234a * j702.n() * Math.min(Math.abs((float) Math.cos(Math.toRadians((double) j702.x()))), Math.abs((float) Math.cos(Math.toRadians((double) j702.y()))));
                            } else {
                                i3 = i8;
                                f10 = B2;
                            }
                            i11 += !iz02.w ? 1 : 0;
                            if (f16 < 0.0f) {
                                b3 = Math.max(0.0f, ((f16 / 0.25f) * b3) + b3);
                            }
                        } else {
                            i3 = i8;
                            f11 = b2;
                            f10 = B2;
                        }
                        float w = (width - j702.w()) / 2.0f;
                        float v = (height - j702.v()) / 2.0f;
                        float w2 = (width - ((hz02.w() * j702.t()) * f10)) / 2.0f;
                        float w3 = (height - ((hz02.w() * j702.s()) * f10)) / 2.0f;
                        if (z4) {
                            f12 = (hz02.A() * w3) + v + b3;
                        } else {
                            if (LocalizationUtils.isLayoutRtl()) {
                                w = (w - (hz02.y() * w2)) - b3;
                            } else {
                                w = (hz02.y() * w2) + w + b3;
                            }
                            f12 = (hz02.z() * w3) + v;
                        }
                        j702.k(J70.l, w);
                        j702.k(J70.m, f12);
                        b2 = f11;
                        i8 = i3;
                        B2 = f10;
                        i12 = (i12 == true ? 1 : 0) + 1;
                    }
                    i2 = i8;
                    if (hz02.R()) {
                        AbstractC5780yZ0 yz02 = hz02.E;
                        if (z4) {
                            f8 = yz02.g0();
                        } else {
                            f8 = yz02.F;
                        }
                        z2 = true;
                        int i13 = 0;
                        for (int length = hz02.f.length - 1; length >= 0; length--) {
                            IZ0 iz03 = hz02.f[length];
                            J70 j703 = iz03.C;
                            if (!iz03.w) {
                                if (z4) {
                                    f9 = j703.C();
                                    j703.k(J70.m, Math.min(f9, f8));
                                } else if (LocalizationUtils.isLayoutRtl()) {
                                    float w4 = hz02.E.F - ((hz02.w() * j703.t()) * B2);
                                    float f18 = (-j703.B()) + w4;
                                    j703.k(J70.l, (-Math.min(f18, f8)) + w4);
                                    f9 = f18;
                                } else {
                                    f9 = j703.B();
                                    j703.k(J70.l, Math.min(f9, f8));
                                }
                                if (f9 >= f8) {
                                    if (i13 < 3) {
                                        f8 -= IZ0.f8234a;
                                        i13++;
                                    }
                                }
                            }
                        }
                    } else {
                        z2 = true;
                    }
                    float s2 = hz02.s();
                    int i14 = 0;
                    while (true) {
                        IZ0[] iz0Arr6 = hz02.f;
                        if (i14 >= iz0Arr6.length) {
                            break;
                        }
                        IZ0 iz04 = iz0Arr6[i14];
                        J70 j704 = iz04.C;
                        float B3 = j704.B() + iz04.l;
                        float C = j704.C() + iz04.m;
                        float f19 = iz04.n;
                        float f20 = iz04.o;
                        float e = AbstractC4089od0.e(f19, B3, iz04.i);
                        float e2 = AbstractC4089od0.e(f20, C, iz04.j);
                        float f21 = iz04.r;
                        if (f21 != 0.0f) {
                            boolean z6 = iz04.u;
                            float g2 = AbstractC2882hZ0.g(f21, s2, z6);
                            float t = iz04.s - (iz04.C.t() / 2.0f);
                            float s3 = iz04.t - (iz04.C.s() / 2.0f);
                            if (z6) {
                                f21 = 0.0f;
                            }
                            float f22 = 1.0f - g2;
                            float f23 = t * f22;
                            if (z4) {
                                f7 = f23 + f21 + e;
                                f6 = s3 * f22;
                            } else {
                                f7 = f23 + e;
                                f6 = (s3 * f22) + f21;
                            }
                            e = f7;
                            e2 += f6;
                        }
                        j704.k(J70.l, rectF.left + e);
                        j704.k(J70.m, rectF.top + e2);
                        i14++;
                    }
                    if (hz02.z != 9) {
                        float f24 = hz02.h;
                        if (f24 >= 0.0f) {
                            f5 = hz02.k;
                        } else {
                            f5 = hz02.l;
                        }
                        if (f24 / f5 < 0.0f && hz02.j >= 5) {
                            hz02.V(j, 9, -1, false);
                            hz02.j = 0;
                            hz02.P(AbstractC4089od0.b(hz02.r, hz02.v(false), hz02.t(false)), false);
                        }
                    }
                    hz02.j();
                    int i15 = hz02.x;
                    if (i15 == -1) {
                        i15 = hz02.h();
                    }
                    AbstractC5780yZ0 yz03 = hz02.E;
                    float f25 = yz03.F;
                    float f26 = yz03.G;
                    f2 = 0.0f;
                    float b4 = AbstractC4089od0.b(rectF.left, 0.0f, f25);
                    float b5 = AbstractC4089od0.b(rectF.right, 0.0f, f25);
                    float b6 = AbstractC4089od0.b(rectF.bottom, 0.0f, f26) - AbstractC4089od0.b(rectF.top, 0.0f, f26);
                    float f27 = f25 * f26;
                    float f28 = 1.0f;
                    float max = (b6 * (b5 - b4)) / Math.max(f27, 1.0f);
                    int i16 = 0;
                    while (true) {
                        IZ0[] iz0Arr7 = hz02.f;
                        if (i16 >= iz0Arr7.length) {
                            break;
                        }
                        IZ0 iz05 = iz0Arr7[i16];
                        iz05.z = max;
                        int i17 = (int) ((iz05.y + f28) / ((max * 0.9f) + 0.1f));
                        iz05.B = i17;
                        iz05.A = (long) ((iz05.x * max) - ((float) i17));
                        IZ0 iz06 = iz0Arr7[i16];
                        iz06.x = iz06.C.o() * iz06.C.p() * ((!iz06.C.h(J70.E) || iz06.C.e(J70.t) <= 0.003921569f) ? 0.0f : 1.0f);
                        float abs = (float) Math.abs(iz06.v - i15);
                        iz06.y = abs;
                        float f29 = iz06.z;
                        int i18 = (int) ((abs + 1.0f) / ((0.9f * f29) + 0.1f));
                        iz06.B = i18;
                        iz06.A = (long) ((iz06.x * f29) - ((float) i18));
                        i16++;
                        f28 = 1.0f;
                    }
                    f3 = f28;
                }
                z = false;
                i8 = i2 + 1;
                f = f3;
                yz0 = this;
            }
            int i19 = 0;
            for (int i20 = 0; i20 < this.a0.size(); i20++) {
                AbstractC2882hZ0 hz03 = (AbstractC2882hZ0) this.a0.get(i20);
                if (hz03.f != null) {
                    int i21 = 0;
                    i = 0;
                    while (true) {
                        IZ0[] iz0Arr8 = hz03.f;
                        if (i21 >= iz0Arr8.length) {
                            break;
                        }
                        if (iz0Arr8[i21].C.F()) {
                            i++;
                        }
                        i21++;
                    }
                } else {
                    i = 0;
                }
                i19 += i;
            }
            if (i19 == 0) {
                this.P = null;
            } else {
                J70[] j70Arr = this.P;
                if (j70Arr == null || j70Arr.length != i19) {
                    this.P = new J70[i19];
                }
            }
            int i22 = 0;
            for (int i23 = 0; i23 < this.a0.size(); i23++) {
                if (i0() != i23) {
                    i22 = Z(i23, this.P, i22);
                }
            }
            Z(i0(), this.P, i22);
            boolean z7 = false;
            for (int i24 = 0; i24 < i19; i24++) {
                if (W(j2, this.P[i24])) {
                    z7 = z2;
                }
            }
            if (z7) {
                M();
            }
            Comparator comparator = this.w0;
            int i25 = 0;
            for (int i26 = 0; i26 < this.a0.size(); i26++) {
                IZ0[] iz0Arr9 = ((AbstractC2882hZ0) this.a0.get(i26)).f;
                i25 += iz0Arr9 != null ? iz0Arr9.length : 0;
            }
            if (i25 == 0) {
                z2 = false;
            } else {
                IZ0[] iz0Arr10 = this.s0;
                if (iz0Arr10 == null || iz0Arr10.length != i25) {
                    this.s0 = new IZ0[i25];
                }
                int i27 = 0;
                for (int i28 = 0; i28 < this.a0.size(); i28++) {
                    IZ0[] iz0Arr11 = this.s0;
                    IZ0[] iz0Arr12 = ((AbstractC2882hZ0) this.a0.get(i28)).f;
                    if (iz0Arr12 != null) {
                        int i29 = i27;
                        int i30 = 0;
                        while (i30 < iz0Arr12.length) {
                            iz0Arr11[i29] = iz0Arr12[i30];
                            i30++;
                            i29++;
                        }
                        i27 = i29;
                    }
                }
                Arrays.sort(this.s0, comparator);
            }
            if (z2) {
                IZ0[] iz0Arr13 = this.s0;
                this.t0.clear();
                for (IZ0 iz07 : iz0Arr13) {
                    this.t0.add(Integer.valueOf(iz07.a()));
                }
                S(this.t0);
                IZ0[] iz0Arr14 = this.s0;
                if (this.x0) {
                    int i31 = 0;
                    for (IZ0 iz08 : iz0Arr14) {
                        if (i31 < 4) {
                            if (super.v(iz08.C)) {
                                i31++;
                            }
                        } else {
                            return;
                        }
                    }
                    if (i31 == 0) {
                        this.x0 = false;
                    }
                }
            }
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void U(RectF rectF, RectF rectF2, LayerTitleCache layerTitleCache, TabContentManager tabContentManager, ResourceManager resourceManager, AbstractC2400ek ekVar) {
        if (this.K0 == null) {
            this.K0 = new Q91();
        }
        this.K0.d(this.f9833J, rectF, this, layerTitleCache, tabContentManager, resourceManager, ekVar, -1, 0.0f, 0);
    }

    public void Y(AbstractC3719mR mRVar, float f, float f2, long j, long j2) {
        C5677xw d = C5677xw.d(m(), this, mRVar, f, f2, j);
        if (j2 < 0) {
            j2 = 0;
        }
        d.Q = j2;
        d.start();
        for (int size = this.G0.size() - 1; size >= 0; size--) {
            if (((Pair) this.G0.get(size)).second == mRVar && !((C5677xw) ((Pair) this.G0.get(size)).first).isRunning()) {
                this.G0.set(size, new Pair(d, mRVar));
                M();
                return;
            }
        }
        this.G0.add(new Pair(d, mRVar));
        M();
    }

    public final int Z(int i, J70[] j70Arr, int i2) {
        IZ0[] iz0Arr = ((AbstractC2882hZ0) this.a0.get(i)).f;
        if (iz0Arr != null) {
            for (IZ0 iz0 : iz0Arr) {
                J70 j70 = iz0.C;
                if (j70.F()) {
                    j70Arr[i2] = j70;
                    i2++;
                }
            }
        }
        return i2;
    }

    @Override // defpackage.AbstractC2300e70
    public void a(ViewGroup viewGroup) {
        ((ViewGroup) viewGroup.getParent()).addView(this.D0, new ViewGroup.LayoutParams(-1, -1));
    }

    public void a0(AbstractC3719mR mRVar) {
        for (int size = this.G0.size() - 1; size >= 0; size--) {
            if (((Pair) this.G0.get(size)).second == mRVar) {
                ((C5677xw) ((Pair) this.G0.get(size)).first).cancel();
            }
        }
    }

    public void b0(long j) {
        for (int i = 0; i < this.a0.size(); i++) {
            ((AbstractC2882hZ0) this.a0.get(i)).n(j);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public void c(Context context) {
        super.c(context);
        float f = IZ0.f8234a;
        Resources resources = context.getResources();
        IZ0.f8234a = ((float) resources.getDimensionPixelOffset(R.dimen.f25220_resource_name_obfuscated_RES_2131166141)) * (1.0f / resources.getDisplayMetrics().density);
        resources.getDimensionPixelOffset(R.dimen.f25210_resource_name_obfuscated_RES_2131166140);
        resources.getDimensionPixelOffset(R.dimen.f25200_resource_name_obfuscated_RES_2131166139);
        Iterator it = this.a0.iterator();
        while (it.hasNext()) {
            ((AbstractC2882hZ0) it.next()).k(context);
        }
        M();
    }

    public abstract int c0(long j, float f, float f2, float f3, float f4);

    public final void d0() {
        AbstractC3719mR mRVar = X;
        a0(mRVar);
        int i02 = i0();
        float abs = Math.abs(((float) i02) + this.g0);
        float f = (float) (-i02);
        if (abs != 0.0f) {
            Y(mRVar, this.g0, f, 100 + ((long) Math.abs((f0() * abs) / this.e0)), 0);
            return;
        }
        this.g0 = f;
        this.h0 = f;
        p0();
    }

    public void e0(int i) {
        s0(i);
        d0();
        M();
    }

    @Override // defpackage.AbstractC2300e70
    public void f() {
        AbstractC0956Pq0 pq0 = this.H0;
        if (pq0 != null) {
            ((C1078Rq0) pq0).I.c(this.J0);
            AbstractC0956Pq0 pq02 = this.H0;
            if (((C1078Rq0) pq02).H != null) {
                ((C1551Zj) ((AbstractC2400ek) ((C1078Rq0) pq02).H)).Y.c(this.I0);
            }
        }
    }

    public final float f0() {
        float f;
        if (m0()) {
            return this.G;
        }
        if (o0()) {
            f = g0();
        } else {
            f = this.F;
        }
        if (this.a0.size() > 2) {
            return f - l0().d();
        }
        return f - (l0().d() * 2.0f);
    }

    @Override // defpackage.AbstractC2300e70
    public void g() {
        if (this.D0.getParent() != null) {
            ((ViewGroup) this.D0.getParent()).removeView(this.D0);
        }
        this.D0.removeAllViews();
    }

    public float g0() {
        float f = this.G;
        float k02 = k0();
        AbstractC2400ek ekVar = (AbstractC2400ek) ((C1078Rq0) this.H0).H;
        return f - (k02 + (ekVar != null ? ((float) AbstractC2571fk.b(ekVar)) / this.c0 : 0.0f));
    }

    @Override // defpackage.AbstractC2300e70
    public void h() {
        if (!this.Z) {
            int i = this.y0;
            AbstractC0124Ca1 ca1 = this.L;
            if (i == ((AbstractC0246Ea1) ca1).e) {
                AbstractC3100ip1.f10165a.d("Tabs.TabOffsetOfSwitch", ((AbstractC0246Ea1) ca1).i().index() - ((AbstractC0246Ea1) this.L).i().i(((AbstractC0246Ea1) this.L).o(this.S)));
            }
        }
        this.Z = false;
        super.h();
        AbstractC3535lK0.a("MobileExitStackView");
        this.j0 = 0.0f;
        this.k0 = 0.0f;
        this.L.d();
    }

    public abstract int h0();

    @Override // defpackage.AbstractC2300e70
    public void i() {
        if (this.M0) {
            super.i();
            AbstractC0956Pq0 pq0 = this.H0;
            if (((C1078Rq0) pq0).H != null) {
                ((C1551Zj) ((AbstractC2400ek) ((C1078Rq0) pq0).H)).Y.b(this.I0);
                y(this.p0, this.q0, this.r0);
            }
        }
    }

    public final int i0() {
        return j0(-1);
    }

    @Override // defpackage.AbstractC2300e70
    public void j() {
        for (int i = 0; i < this.G0.size(); i++) {
            ((C5677xw) ((Pair) this.G0.get(i)).first).end();
        }
        this.G0.clear();
    }

    public abstract int j0(int i);

    public float k0() {
        AbstractC2400ek ekVar = (AbstractC2400ek) ((C1078Rq0) this.H0).H;
        if (ekVar != null) {
            return ((float) ((C1551Zj) ekVar).T) / this.c0;
        }
        return 0.0f;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean l() {
        return true;
    }

    public final C5270vZ0 l0() {
        if (o0()) {
            if (this.C0 == null) {
                this.C0 = new C4930tZ0(this);
            }
            return this.C0;
        }
        if (this.B0 == null) {
            this.B0 = new C5270vZ0(this);
        }
        return this.B0;
    }

    public boolean m0() {
        return CachedFeatureFlags.isEnabled("HorizontalTabSwitcherAndroid");
    }

    @Override // defpackage.AbstractC2300e70
    public VL n() {
        return this.E0;
    }

    public boolean n0() {
        for (int i = 0; i < this.G0.size(); i++) {
            if (((C5677xw) ((Pair) this.G0.get(i)).first).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public final boolean o0() {
        return this.K == 2 || m0();
    }

    @Override // defpackage.AbstractC2300e70
    public int p() {
        return 1;
    }

    public abstract void p0();

    @Override // defpackage.AbstractC2300e70
    public SceneLayer q() {
        return this.K0;
    }

    public abstract void q0();

    @Override // defpackage.AbstractC2300e70
    public int r() {
        return 0;
    }

    public final void r0() {
        float f = (float) (-i0());
        this.h0 = f;
        this.g0 = f;
    }

    @Override // defpackage.AbstractC2300e70
    public boolean s() {
        return true;
    }

    public abstract void s0(int i);

    @Override // defpackage.AbstractC2300e70
    public boolean t() {
        return true;
    }

    public void t0(boolean z) {
        boolean z2 = true;
        if (this.a0.size() < 2 || !((AbstractC2882hZ0) this.a0.get(1)).F()) {
            z2 = false;
        }
        u0(z, z2);
    }

    @Override // defpackage.AbstractC2300e70
    public boolean u() {
        return true;
    }

    public void u0(boolean z, boolean z2) {
        AbstractC3719mR mRVar = V;
        a0(mRVar);
        float f = this.j0;
        float f2 = (!z || !z2) ? 0.0f : 1.0f;
        if (f != f2) {
            Y(mRVar, f, f2, 200, 0);
        }
    }

    @Override // defpackage.AbstractC2300e70
    public boolean v(J70 j70) {
        if (!(!j70.h(J70.S))) {
            return false;
        }
        this.x0 = true;
        return false;
    }

    public final void v0(boolean z) {
        AbstractC3719mR mRVar = W;
        a0(mRVar);
        float f = this.k0;
        float f2 = z ? 1.0f : 0.0f;
        if (f != f2) {
            Y(mRVar, f, f2, 300, 0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005d, code lost:
        if ((((defpackage.AbstractC0246Ea1) r10.L).l(false).getCount() + ((defpackage.AbstractC0246Ea1) r10.L).l(true).getCount()) >= 2) goto L_0x005f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void w0(long r11, int r13, boolean r14, boolean r15) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC5780yZ0.w0(long, int, boolean, boolean):void");
    }

    public abstract void x0(long j, int i);

    @Override // defpackage.AbstractC2300e70
    public void y(float f, float f2, int i) {
        this.p0 = f;
        this.q0 = f2;
        this.r0 = i;
        this.C0 = null;
        this.B0 = null;
        Iterator it = this.a0.iterator();
        while (it.hasNext()) {
            ((AbstractC2882hZ0) it.next()).G(f, f2, i);
        }
        r0();
        M();
    }

    @Override // defpackage.AbstractC2300e70
    public boolean z() {
        L(SystemClock.currentThreadTimeMillis(), true);
        return false;
    }
}
