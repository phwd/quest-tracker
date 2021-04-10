package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.Objects;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: hZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2882hZ0 {

    /* renamed from: a  reason: collision with root package name */
    public static final float f10079a = ((float) Math.tan(Math.toRadians(30.0d)));
    public static final AbstractC3719mR b = new C2711gZ0("SCROLL_OFFSET");
    public C3223jZ0 A;
    public MZ0 B;
    public C3053iZ0 C;
    public Animator D;
    public final AbstractC5780yZ0 E;
    public float F;
    public float G;
    public float H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public float f10080J;
    public boolean K;
    public final AnimatorListenerAdapter L = new C2540fZ0(this);
    public N81 c;
    public boolean d;
    public int e;
    public IZ0[] f;
    public AZ0 g;
    public float h;
    public int i;
    public int j;
    public float k;
    public float l;
    public float m;
    public int n = 0;
    public long o;
    public float p;
    public float q;
    public float r;
    public float s;
    public float t;
    public IZ0 u;
    public IZ0 v;
    public float w = Float.NaN;
    public int x = -1;
    public int y = 1;
    public int z = 10;

    public AbstractC2882hZ0(Context context, AbstractC5780yZ0 yz0) {
        this.E = yz0;
        k(context);
    }

    public static float f(float f2, float f3) {
        if (Math.abs(f2) < 1.0f) {
            return 1.0f;
        }
        return 1.0f - Math.abs(AbstractC4089od0.b(f2 / f3, -1.0f, 1.0f));
    }

    public static float g(float f2, float f3, boolean z2) {
        if (Math.abs(f2) < 1.0f) {
            return 1.0f;
        }
        return AbstractC4089od0.e(1.0f, z2 ? 0.7f : 0.5f, Math.abs(f2 / f3));
    }

    public static RectF q(J70 j70) {
        float e2 = j70.e(J70.x) * 4.0f;
        RectF rectF = (RectF) j70.g(J70.O);
        RH0 rh0 = J70.m;
        float e3 = j70.e(rh0);
        RH0 rh02 = J70.q;
        rectF.top = (j70.e(rh02) + e3) - e2;
        rectF.bottom = j70.o() + j70.e(rh02) + j70.e(rh0) + e2;
        RH0 rh03 = J70.l;
        float e4 = j70.e(rh03);
        RH0 rh04 = J70.p;
        rectF.left = (j70.e(rh04) + e4) - e2;
        float e5 = j70.e(rh03);
        rectF.right = j70.p() + j70.e(rh04) + e5 + e2;
        return rectF;
    }

    public abstract float A();

    public final float B(RectF rectF) {
        float f2;
        float f3;
        if (this.y == 1) {
            f2 = rectF.width();
            f3 = this.E.F;
        } else {
            f2 = rectF.height();
            f3 = this.E.g0();
        }
        return f2 / f3;
    }

    public int C(float f2, float f3) {
        return D(f2, f3, 0.0f);
    }

    public final int D(float f2, float f3, float f4) {
        int i2;
        AbstractC5780yZ0 yz0 = this.E;
        float f5 = yz0.G + yz0.F;
        IZ0[] iz0Arr = this.f;
        if (iz0Arr != null) {
            i2 = iz0Arr.length - 1;
            int i3 = -1;
            while (true) {
                if (i2 < 0) {
                    i2 = i3;
                    break;
                }
                IZ0[] iz0Arr2 = this.f;
                if (!iz0Arr2[i2].w && iz0Arr2[i2].C.F()) {
                    RectF q2 = q(this.f[i2].C);
                    float max = Math.max(0.0f, Math.max(Math.max(q2.left - f2, f2 - q2.right), Math.max(q2.top - f3, f3 - q2.bottom)));
                    if (max >= f5) {
                        continue;
                    } else if (max == 0.0f) {
                        f5 = max;
                        break;
                    } else {
                        i3 = i2;
                        f5 = max;
                    }
                }
                i2--;
            }
        } else {
            i2 = -1;
        }
        if (f5 <= f4) {
            return i2;
        }
        return -1;
    }

    public final boolean E() {
        boolean z2 = true;
        if (N.M09VlOh_("HorizontalTabSwitcherAndroid")) {
            return !LocalizationUtils.isLayoutRtl();
        }
        if (this.y != 1) {
            z2 = false;
        }
        return LocalizationUtils.isLayoutRtl() ^ z2;
    }

    public boolean F() {
        N81 n81 = this.c;
        if (n81 == null) {
            return false;
        }
        if (!n81.a() || (!this.d && this.c.getCount() > 0)) {
            return true;
        }
        return false;
    }

    public void G(float f2, float f3, int i2) {
        Y(i2);
        if (this.f != null) {
            boolean E2 = E();
            int i3 = 0;
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i3 < iz0Arr.length) {
                    iz0Arr[i3].C.j(J70.N, E2);
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void H(long j2) {
        this.n = 0;
        if (this.z == 10) {
            W(j2);
        }
        this.u = null;
        e(j2, false);
    }

    public abstract void I(long j2, float f2, float f3);

    public abstract void J(long j2, float f2, float f3, float f4, float f5, boolean z2);

    public void K(long j2) {
        e(j2, true);
        M();
        T(j2);
    }

    public abstract void L();

    public void M() {
        this.u = null;
        this.v = null;
    }

    public abstract float N(float f2);

    public abstract float O(float f2);

    public void P(float f2, boolean z2) {
        boolean a2 = a();
        float b2 = AbstractC4089od0.b(f2, v(a2), t(a2));
        this.q = b2;
        if (z2) {
            this.r = b2;
        }
        this.t = Math.signum(b2 - this.r);
    }

    public abstract boolean Q();

    public abstract boolean R();

    public abstract boolean S();

    public abstract void T(long j2);

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public final void U(long j2, int i2, int i3, int i4, boolean z2) {
        MZ0 mz0;
        AnimatorSet animatorSet;
        C3053iZ0 iz0;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        Tab tabAt;
        View b2;
        int i5;
        int i6 = 0;
        if (!(this.A != null && ((i5 = this.z) == 5 || i5 == 7 || i5 == 6) && (i2 == 5 || i2 == 7 || i2 == 6))) {
            n(j2);
            W(j2);
        }
        if (!(this.A == null || (mz0 = this.B) == null)) {
            this.z = i2;
            IZ0[] iz0Arr = this.f;
            ViewGroup viewGroup = this.E.D0;
            N81 n81 = this.c;
            Objects.requireNonNull(mz0);
            float f8 = 0.0f;
            if (n81 == null || i2 != 1 || (tabAt = n81.getTabAt(i3)) == null || !tabAt.isNativePage() || (b2 = tabAt.b()) == null) {
                animatorSet = null;
            } else {
                if (b2.getParent() != null) {
                    ((ViewGroup) b2.getParent()).removeView(b2);
                }
                FrameLayout frameLayout = new FrameLayout(b2.getContext());
                frameLayout.setBackgroundColor(AbstractC1300Vg1.a(tabAt));
                frameLayout.addView(b2);
                viewGroup.addView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
                if (iz0Arr != null && i3 >= 0 && i3 < iz0Arr.length) {
                    iz0Arr[i3].p = 0.0f;
                }
                ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(b2, PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f, 1.0f));
                ofPropertyValuesHolder.setDuration(350L);
                animation.InterpolatorC2176dO dOVar = G30.c;
                ofPropertyValuesHolder.setInterpolator(dOVar);
                ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(b2, PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, (float) mz0.f8484a, 0.0f));
                ofPropertyValuesHolder2.setDuration(350L);
                ofPropertyValuesHolder2.setInterpolator(dOVar);
                ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(frameLayout, PropertyValuesHolder.ofFloat(View.ALPHA, 0.0f, 1.0f));
                ofPropertyValuesHolder3.setDuration(150L);
                ofPropertyValuesHolder3.setInterpolator(dOVar);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(ofPropertyValuesHolder, ofPropertyValuesHolder2, ofPropertyValuesHolder3);
            }
            this.D = animatorSet;
            if (animatorSet != null) {
                animatorSet.addListener(this.L);
            } else {
                C3223jZ0 jz0 = this.A;
                IZ0[] iz0Arr2 = this.f;
                int i7 = this.e;
                float s2 = s();
                Objects.requireNonNull(jz0);
                if (iz0Arr2 != null) {
                    iz0 = new C3053iZ0(jz0, this.E.m());
                    float f9 = 1.0f;
                    switch (i2) {
                        case 0:
                            if (jz0.g == 2) {
                                float N = jz0.f.N(0.0f);
                                int i8 = 0;
                                while (i8 < iz0Arr2.length) {
                                    IZ0 iz02 = iz0Arr2[i8];
                                    iz02.c();
                                    iz02.q = jz0.f.w();
                                    iz02.p = 1.0f;
                                    J70 j70 = iz02.C;
                                    float f10 = i8 == i3 ? 1.0f : 0.0f;
                                    RH0 rh0 = J70.f8270J;
                                    j70.k(rh0, f10);
                                    iz02.C.k(J70.x, 1.0f);
                                    float N2 = jz0.f.N((float) (i8 * i7));
                                    J70 j702 = iz02.C;
                                    iz0.a(j702, J70.B, j702.A(), jz0.f.u(), 300);
                                    if (i8 < i3) {
                                        iz0.b(iz02, IZ0.d, N, N2, 300, null);
                                    } else if (i8 > i3) {
                                        iz02.k = N2;
                                        iz0.b(iz02, IZ0.f, (jz0.f10213a <= jz0.b || !LocalizationUtils.isLayoutRtl()) ? jz0.f10213a : -jz0.f10213a, 0.0f, 300, null);
                                    } else {
                                        iz02.k = N2;
                                        iz0.b(iz02, IZ0.e, 0.0f, 1.0f, 200, null);
                                        iz0.b(iz02, IZ0.c, 1.0f, jz0.f.w(), 200, null);
                                        iz0.a(iz02.C, J70.L, 0.0f, jz0.c(), 200);
                                        iz0.a(iz02.C, J70.M, 0.0f, 1.0f, 200);
                                        iz0.c(iz02.C, rh0, 1.0f, 0.0f, 100, 100);
                                    }
                                    i8++;
                                }
                                break;
                            } else {
                                float N3 = jz0.f.N(0.0f);
                                if (i3 < 0 || i3 >= iz0Arr2.length - 1) {
                                    f2 = 0.0f;
                                    f3 = 0.0f;
                                } else {
                                    f2 = 0.0f;
                                    f3 = Math.max((iz0Arr2[i3].k - iz0Arr2[i3 + 1].k) + (i3 == 0 ? (float) i7 : 0.0f) + (iz0Arr2[i3].C.v() * 0.35f), 0.0f);
                                }
                                int i9 = 0;
                                while (i9 < iz0Arr2.length) {
                                    IZ0 iz03 = iz0Arr2[i9];
                                    iz03.c();
                                    iz03.q = jz0.f.w();
                                    iz03.p = 1.0f;
                                    J70 j703 = iz03.C;
                                    float f11 = i9 == i3 ? 1.0f : f2;
                                    RH0 rh02 = J70.f8270J;
                                    j703.k(rh02, f11);
                                    iz03.C.k(J70.x, 1.0f);
                                    float N4 = jz0.f.N((float) (i9 * i7));
                                    if (i9 < i3) {
                                        iz03.C.k(J70.B, jz0.f.u());
                                        iz0.b(iz03, IZ0.d, N3, N4, 300, null);
                                    } else if (i9 > i3) {
                                        iz03.C.k(J70.B, jz0.f.u());
                                        iz03.k = N4 + f3;
                                        iz0.b(iz03, IZ0.h, jz0.b, 0.0f, 300, null);
                                    } else {
                                        iz03.k = N4;
                                        J70 j704 = iz03.C;
                                        iz0.c(j704, J70.B, j704.A(), jz0.f.u(), 300, 10);
                                        iz0.b(iz03, IZ0.g, 0.0f, 1.0f, 200, null);
                                        iz0.b(iz03, IZ0.c, 1.0f, jz0.f.w(), 200, null);
                                        iz0.a(iz03.C, J70.L, 0.0f, jz0.c(), 200);
                                        iz0.a(iz03.C, J70.M, 0.0f, 1.0f, 200);
                                        iz0.c(iz03.C, rh02, 1.0f, 0.0f, 200, 100);
                                        iz03.o = jz0.c - jz0.d;
                                    }
                                    i9++;
                                }
                                break;
                            }
                            break;
                        case 1:
                            if (jz0.g != 2) {
                                for (int i10 = 0; i10 < iz0Arr2.length; i10++) {
                                    iz0.b(iz0Arr2[i10], IZ0.d, iz0Arr2[i10].k, 0.0f, 300, null);
                                }
                                break;
                            }
                            break;
                        case 2:
                            for (int i11 = 0; i11 < iz0Arr2.length; i11++) {
                                IZ0 iz04 = iz0Arr2[i11];
                                J70 j705 = iz04.C;
                                jz0.a(iz0, j705, 0.0f, 400);
                                iz0.b(iz04, IZ0.b, iz04.r, 0.0f, 400, null);
                                if (i11 < i3) {
                                    AbstractC3719mR mRVar = IZ0.d;
                                    float f12 = iz04.k;
                                    if (jz0.g == 2) {
                                        f5 = Math.max(0.0f, (f12 - jz0.f10213a) - ((float) i7));
                                    } else {
                                        f5 = (f12 - jz0.b) - ((float) i7);
                                    }
                                    iz0.b(iz04, mRVar, f12, f5, 400, null);
                                } else if (i11 <= i3) {
                                    iz04.n = 0.0f;
                                    iz04.o = 0.0f;
                                    j705.k(J70.x, 1.0f);
                                    if (jz0.g == 2) {
                                        iz0.b(iz04, IZ0.e, iz04.i, 0.0f, 400, null);
                                        if (!jz0.d()) {
                                            iz0.b(iz04, IZ0.d, iz04.k, jz0.f.N(0.0f), 400, null);
                                        }
                                    } else {
                                        AbstractC3719mR mRVar2 = IZ0.d;
                                        float f13 = iz04.k;
                                        iz0.b(iz04, mRVar2, f13, Math.max(0.0f, (f13 - jz0.f10213a) - ((float) i7)), 400, null);
                                    }
                                    iz0.b(iz04, IZ0.c, iz04.q, 1.0f, 400, null);
                                    iz0.b(iz04, IZ0.g, iz04.j, 0.0f, 200, null);
                                    J70 j706 = iz04.C;
                                    iz0.a(j706, J70.B, j706.r(), iz04.C.A(), 400);
                                    iz04.o = jz0.c - jz0.d;
                                    if (j705.h(J70.F)) {
                                        iz0.a(j705, J70.u, 1.0f, 0.0f, 200);
                                    }
                                    iz0.a(iz04.C, J70.f8270J, j705.z(), 1.0f, 250);
                                    iz0.a(iz04.C, J70.L, jz0.c(), 0.0f, 250);
                                    iz0.a(iz04.C, J70.M, 1.0f, 0.0f, 250);
                                } else if (jz0.g == 2) {
                                    float B2 = j705.B();
                                    if (LocalizationUtils.isLayoutRtl()) {
                                        f4 = j705.w() + B2;
                                    } else {
                                        f4 = jz0.f10213a - B2;
                                    }
                                    float b3 = (AbstractC4089od0.b(f4, 0.0f, jz0.f10213a) * 100.0f) / jz0.f10213a;
                                    AbstractC3719mR mRVar3 = IZ0.f;
                                    float f14 = iz04.l;
                                    long j3 = (long) b3;
                                    iz0.d(iz04, mRVar3, f14, (LocalizationUtils.isLayoutRtl() ? -jz0.f10213a : jz0.f10213a) + f14, 400 - j3, j3, null);
                                } else {
                                    float C2 = j705.C();
                                    float f15 = jz0.b;
                                    float f16 = jz0.b;
                                    AbstractC3719mR mRVar4 = IZ0.h;
                                    float f17 = iz04.m;
                                    long b4 = (long) ((AbstractC4089od0.b(f15 - C2, 0.0f, f15) * 100.0f) / f16);
                                    iz0.d(iz04, mRVar4, f17, f17 + f16, 400 - b4, b4, null);
                                }
                            }
                            break;
                        case 3:
                            int i12 = i4 + 1;
                            if (i12 < iz0Arr2.length) {
                                if (jz0.g == 2) {
                                    f6 = iz0Arr2[i4].C.w();
                                } else {
                                    f6 = iz0Arr2[i4].C.v();
                                }
                                float max = Math.max(200.0f, ((f6 * 0.75f) + iz0Arr2[i4].k) - iz0Arr2[i12].k);
                                while (i12 < iz0Arr2.length) {
                                    iz0.b(iz0Arr2[i12], IZ0.d, iz0Arr2[i12].k, iz0Arr2[i12].k + max, 400, null);
                                    i12++;
                                }
                                break;
                            }
                            break;
                        case 4:
                            int i13 = 0;
                            float f18 = 0.0f;
                            while (i13 < iz0Arr2.length && f18 < jz0.b(iz0Arr2[i13])) {
                                iz0.b(iz0Arr2[i13], IZ0.d, iz0Arr2[i13].k, jz0.f.N(f18), 400, null);
                                if (jz0.g == 2) {
                                    f7 = iz0Arr2[i13].C.w();
                                } else {
                                    f7 = iz0Arr2[i13].C.v();
                                }
                                f18 += f7;
                                i13++;
                            }
                            break;
                        case 5:
                        case 6:
                        case Version.VERSION_7:
                            int i14 = 0;
                            float f19 = 0.0f;
                            int i15 = -1;
                            for (int i16 = 0; i16 < iz0Arr2.length; i16++) {
                                jz0.a(iz0, iz0Arr2[i16].C, 0.0f, 150);
                                if (iz0Arr2[i16].w && (i14 = i14 + 1) == 1) {
                                    f19 = jz0.b(iz0Arr2[i16]);
                                    i15 = i16;
                                }
                            }
                            int i17 = jz0.g;
                            float f20 = i17 == 2 ? jz0.f10213a : jz0.b;
                            boolean z3 = i17 == 2 || !LocalizationUtils.isLayoutRtl();
                            int i18 = 0;
                            int i19 = 0;
                            while (i18 < iz0Arr2.length) {
                                IZ0 iz05 = iz0Arr2[i18];
                                if (!jz0.d()) {
                                    Math.max(f8, (jz0.b(iz05) - f19) * (400.0f / f20));
                                }
                                if (iz05.w) {
                                    float f21 = iz05.r;
                                    if (f21 == f8) {
                                        f21 = z3 ? f8 : -0.0f;
                                    }
                                    iz0.b(iz05, IZ0.b, f21, s2 * Math.copySign(f9, f21), (long) ((f9 - Math.abs(f21 / s2)) * 150.0f), animation.InterpolatorC5286vf.f);
                                    i19 = i19;
                                } else {
                                    float f22 = iz05.r;
                                    if (f22 != 0.0f) {
                                        iz0.b(iz05, IZ0.b, f22, 0.0f, 150, null);
                                    }
                                    iz0.b(iz05, IZ0.c, iz05.q, jz0.f.w(), 150, null);
                                    J70 j707 = iz05.C;
                                    iz0.a(j707, J70.B, j707.r(), jz0.f.u(), 150);
                                    float N5 = jz0.f.N((float) (i7 * i19));
                                    if (iz05.r >= s2) {
                                        iz05.k = N5;
                                        iz05.q = jz0.f.w();
                                    } else {
                                        float f23 = iz05.k;
                                        if (f23 != N5) {
                                            iz0.b(iz05, IZ0.d, f23, N5, 500, null);
                                        }
                                    }
                                    i19++;
                                }
                                i18++;
                                f9 = 1.0f;
                                f8 = 0.0f;
                            }
                            if (jz0.d()) {
                                C5147up0 up0 = (C5147up0) this;
                                int a0 = up0.a0();
                                if ((i15 == iz0Arr2.length - 1 && i15 == a0) || (i15 != -1 && i15 < a0)) {
                                    up0.M = true;
                                    iz0.b(up0, b, this.r, ((float) (-(a0 - 1))) * ((float) this.e), 500, null);
                                    break;
                                }
                            }
                            break;
                        case Version.VERSION_8:
                            while (i6 < iz0Arr2.length) {
                                jz0.a(iz0, iz0Arr2[i6].C, 0.0f, 75);
                                i6++;
                            }
                            break;
                        case Version.VERSION_9:
                            while (i6 < iz0Arr2.length) {
                                J70 j708 = iz0Arr2[i6].C;
                                j708.k(J70.h, j708.x());
                                j708.k(J70.j, j708.v() / 2.0f);
                                j708.k(J70.i, j708.y());
                                j708.k(J70.k, j708.w() / 2.0f);
                                jz0.a(iz0, j708, -360.0f, 1000);
                                i6++;
                            }
                            break;
                    }
                    this.C = iz0;
                }
                iz0 = null;
                this.C = iz0;
            }
            C3053iZ0 iz06 = this.C;
            if (iz06 != null) {
                iz06.b.playTogether(iz06.f10144a);
                iz06.b.start();
            }
            Animator animator = this.D;
            if (animator != null) {
                animator.start();
            }
            C3053iZ0 iz07 = this.C;
            if (!(iz07 == null && this.D == null)) {
                this.E.d0++;
            }
            if ((iz07 == null && this.D == null) || z2) {
                n(j2);
            }
        }
        this.E.M();
    }

    public void V(long j2, int i2, int i3, boolean z2) {
        U(j2, i2, this.c.index(), i3, z2);
    }

    public final void W(long j2) {
        if (this.g.a(j2)) {
            P((float) this.g.e.e, true);
            this.g.c(true);
            return;
        }
        P(this.r, false);
    }

    public void X(long j2, int i2) {
        if (this.f != null) {
            int i3 = 0;
            boolean z2 = false;
            int i4 = 0;
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i3 >= iz0Arr.length) {
                    break;
                }
                if (iz0Arr[i3].a() == i2) {
                    IZ0[] iz0Arr2 = this.f;
                    z2 |= !iz0Arr2[i3].w;
                    iz0Arr2[i3].w = true;
                } else {
                    this.f[i3].v = i4;
                    i4++;
                }
                i3++;
            }
            if (z2) {
                this.s = this.r;
                this.e = i(i4);
                V(j2, 5, -1, false);
            }
            if (i4 == 0) {
                this.d = true;
            }
        }
    }

    public void Y(int i2) {
        if (CachedFeatureFlags.isEnabled("HorizontalTabSwitcherAndroid")) {
            this.y = 2;
        } else {
            this.y = i2;
        }
        this.w = r();
        float f2 = this.H - this.F;
        AbstractC5780yZ0 yz0 = this.E;
        this.A = new C3223jZ0(this, yz0.F, yz0.G, yz0.k0(), this.H, f2, this.I, this.y);
        this.B = new MZ0(this.E.f9833J.getResources());
        if (this.f != null) {
            float f3 = this.E.F;
            int i3 = 0;
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i3 < iz0Arr.length) {
                    J70 j70 = iz0Arr[i3].C;
                    if (j70 != null) {
                        j70.k(J70.A, f3);
                        j70.k(J70.B, u());
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public final void Z() {
        float b2 = AbstractC4089od0.b(this.r, v(false), t(false));
        if (!a()) {
            this.r = b2;
        }
        float f2 = this.r - b2;
        int signum = (int) Math.signum(Math.abs(this.h) - Math.abs(f2));
        if (signum != this.i && signum == 1 && f2 < 0.0f) {
            this.j++;
        } else if (f2 > 0.0f || this.y == 2) {
            this.j = 0;
        }
        this.i = signum;
        this.h = f2;
    }

    public abstract boolean a();

    public float b(IZ0 iz0, float f2) {
        return O(iz0.k + f2);
    }

    public final void c() {
        IZ0[] iz0Arr;
        if (this.f != null) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iz0Arr = this.f;
                if (i2 >= iz0Arr.length) {
                    break;
                }
                if (iz0Arr[i2].w) {
                    this.E.N.d(iz0Arr[i2].C.q());
                } else {
                    i3++;
                }
                i2++;
            }
            if (i3 == 0) {
                d();
            } else if (i3 < iz0Arr.length) {
                this.f = new IZ0[i3];
                int i4 = 0;
                for (int i5 = 0; i5 < iz0Arr.length; i5++) {
                    if (!iz0Arr[i5].w) {
                        IZ0[] iz0Arr2 = this.f;
                        iz0Arr2[i4] = iz0Arr[i5];
                        iz0Arr2[i4].v = i4;
                        i4++;
                    }
                }
            }
        }
        this.w = r();
    }

    public void d() {
        this.f = null;
        M();
    }

    public void e(long j2, boolean z2) {
        IZ0 iz0 = this.v;
        if (iz0 != null) {
            if (Math.abs(iz0.r) / s() <= 0.4f || !z2) {
                V(j2, 7, -1, false);
            } else {
                this.E.x0(j2, iz0.a());
                AbstractC3535lK0.a("MobileStackViewSwipeCloseTab");
                AbstractC3535lK0.a("MobileTabClosed");
            }
            this.v = null;
            this.E.M();
        }
    }

    public abstract int h();

    public abstract int i(int i2);

    public abstract void j();

    public void k(Context context) {
        Resources resources = context.getResources();
        float f2 = 1.0f / resources.getDisplayMetrics().density;
        this.p = 1.25f;
        float dimensionPixelOffset = (float) resources.getDimensionPixelOffset(R.dimen.f23410_resource_name_obfuscated_RES_2131165960);
        this.k = dimensionPixelOffset * f2;
        this.l = ((float) Math.round(2.0f * dimensionPixelOffset)) * f2;
        this.m = (float) resources.getInteger(R.integer.f36090_resource_name_obfuscated_RES_2131492906);
        resources.getDimensionPixelOffset(R.dimen.f23420_resource_name_obfuscated_RES_2131165961);
        this.F = resources.getDimension(R.dimen.f25840_resource_name_obfuscated_RES_2131166203) * f2;
        this.G = resources.getDimension(R.dimen.f25830_resource_name_obfuscated_RES_2131166202) * f2;
        this.H = resources.getDimension(R.dimen.f25820_resource_name_obfuscated_RES_2131166201) * f2;
        this.I = resources.getDimension(R.dimen.f25810_resource_name_obfuscated_RES_2131166200) * f2;
        this.f10080J = resources.getDimension(R.dimen.f17460_resource_name_obfuscated_RES_2131165365) * f2;
        this.g = new AZ0(context);
    }

    public final void l(boolean z2) {
        float f2;
        float f3;
        N81 n81 = this.c;
        if (n81 != null) {
            int count = n81.getCount();
            if (count == 0) {
                d();
                return;
            }
            IZ0[] iz0Arr = this.f;
            this.f = new IZ0[count];
            boolean a2 = this.c.a();
            boolean z3 = !this.E.Q;
            int i2 = 0;
            while (i2 < count) {
                Tab tabAt = this.c.getTabAt(i2);
                int id = tabAt != null ? tabAt.getId() : -1;
                IZ0[] iz0Arr2 = this.f;
                IZ0 iz0 = null;
                if (iz0Arr != null) {
                    int length = iz0Arr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        } else if (iz0Arr[i3].a() == id) {
                            iz0 = iz0Arr[i3];
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
                iz0Arr2[i2] = iz0;
                IZ0[] iz0Arr3 = this.f;
                if (iz0Arr3[i2] == null || iz0Arr3[i2].C == null || !z2) {
                    f3 = -1.0f;
                    f2 = -1.0f;
                } else {
                    f3 = iz0Arr3[i2].C.e(J70.A);
                    f2 = this.f[i2].C.r();
                }
                J70 e2 = this.E.e(id, a2, true, z3, f3, f2);
                e2.j(J70.K, true);
                e2.j(J70.H, true);
                e2.k(J70.f8270J, 0.0f);
                e2.j(J70.I, !this.K || this.c.index() != i2);
                e2.j(J70.N, E());
                IZ0[] iz0Arr4 = this.f;
                if (iz0Arr4[i2] == null) {
                    iz0Arr4[i2] = new IZ0(e2);
                } else {
                    iz0Arr4[i2].C = e2;
                }
                this.f[i2].v = i2;
                i2++;
            }
        }
    }

    public abstract boolean m(float f2, boolean z2);

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        if (r0 != 7) goto L_0x00af;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void n(long r11) {
        /*
        // Method dump skipped, instructions count: 204
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC2882hZ0.n(long):void");
    }

    public final void o(long j2, boolean z2) {
        Animator animator = this.D;
        boolean z3 = false;
        boolean z4 = animator != null;
        boolean z5 = !z4 || !animator.isRunning();
        C3053iZ0 iz0 = this.C;
        boolean z6 = iz0 != null;
        boolean z7 = !z6 || !iz0.b.isRunning();
        boolean z8 = z4 || z6;
        boolean z9 = z2 && z8;
        if (z8 && ((!z4 || z5) && (!z6 || z7))) {
            z3 = true;
        }
        if (z9 || z3) {
            n(j2);
        }
    }

    public void p() {
        this.g.c(true);
        Z();
        this.q = this.r;
    }

    public final float r() {
        return (this.y != 2 || !LocalizationUtils.isLayoutRtl()) ? 1.0f : -1.0f;
    }

    public final float s() {
        float f2;
        if (this.y == 1) {
            f2 = this.E.F;
        } else {
            f2 = this.E.g0();
        }
        return 0.7f * f2;
    }

    public float t(boolean z2) {
        if (this.f == null || !z2) {
            return 0.0f;
        }
        return this.k;
    }

    public abstract float u();

    public abstract float v(boolean z2);

    public abstract float w();

    public float x() {
        if (this.y == 1) {
            return this.E.g0();
        }
        return this.E.F;
    }

    public abstract float y();

    public abstract float z();
}
