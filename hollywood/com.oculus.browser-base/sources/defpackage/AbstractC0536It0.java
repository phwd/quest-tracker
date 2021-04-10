package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: It0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0536It0 {
    public final float F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public float f8254J;
    public final Context K;
    public int L = 0;
    public final int M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;
    public boolean V;
    public final float W;
    public final float X;
    public float Y;
    public boolean Z;
    public float a0;
    public float b0;
    public float c0;
    public float d0;
    public float e0;
    public float f0 = 1.0f;
    public float g0;
    public boolean h0;
    public float i0;
    public float j0;
    public ViewGroup k0;
    public IJ l0;
    public Integer m0;
    public int n0;
    public C5677xw o0;
    public final K70 p0;

    public AbstractC0536It0(Context context, K70 k70) {
        this.K = context;
        float f = 1.0f / context.getResources().getDisplayMetrics().density;
        this.F = f;
        this.W = 12.0f;
        this.X = 10.0f;
        this.i0 = 2.0f;
        this.a0 = 1.0f;
        this.Y = context.getResources().getDimension(R.dimen.f23490_resource_name_obfuscated_RES_2131165968) * f;
        Resources resources = context.getResources();
        this.G = resources.getColor(R.color.f14490_resource_name_obfuscated_RES_2131100139);
        this.H = resources.getColor(R.color.f11220_resource_name_obfuscated_RES_2131099812);
        this.I = resources.getColor(R.color.f12180_resource_name_obfuscated_RES_2131099908);
        this.M = (int) (resources.getDimension(R.dimen.f23500_resource_name_obfuscated_RES_2131165969) * f);
        this.p0 = k70;
    }

    public boolean A(int i) {
        return i == ((C1796bA) this).L && AbstractC4089od0.a(this.U, J(Integer.valueOf(i)));
    }

    public final int B(float f) {
        for (int i = 0; i < 5; i++) {
            if (Q(i) && f <= J(Integer.valueOf(i))) {
                return i;
            }
        }
        return 1;
    }

    public int C(float f, float f2) {
        if (f < 0.0f) {
            return 1;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i >= 5) {
                i = i2;
                i2 = i3;
                break;
            }
            if (Q(i)) {
                if (f >= J(Integer.valueOf(i2)) && f < J(Integer.valueOf(i))) {
                    break;
                }
                i3 = i2;
                i2 = i;
            }
            i++;
        }
        float J2 = J(Integer.valueOf(i2));
        return (f - J2) / (J(Integer.valueOf(i)) - J2) > 0.5f ? i : i2;
    }

    public C4316pw D() {
        return ((D70) this.p0).h0;
    }

    public abstract float E();

    public float F() {
        float f;
        if (M()) {
            f = this.O;
        } else {
            f = this.O - 0.0f;
        }
        return f * 0.7f;
    }

    public final int G() {
        if (P(4)) {
            return 4;
        }
        if (P(3)) {
            return 3;
        }
        return 2;
    }

    public int H() {
        return Math.round(this.Q / this.F);
    }

    public float I() {
        if (this.d0 == 0.0f) {
            this.d0 = ((float) AbstractC3153j7.c(this.K.getResources(), R.drawable.f34370_resource_name_obfuscated_RES_2131231477).getIntrinsicWidth()) * this.F;
        }
        return this.d0;
    }

    public float J(Integer num) {
        if (num == null) {
            return 0.0f;
        }
        if (num.intValue() == 2) {
            C1796bA bAVar = (C1796bA) this;
            float f = bAVar.Y;
            Objects.requireNonNull(bAVar.o0());
            return (bAVar.F * 0.0f) + f;
        } else if (num.intValue() == 3) {
            return F();
        } else {
            if (num.intValue() == 4) {
                return 0.95f * ((C1796bA) this).O;
            }
            return 0.0f;
        }
    }

    public final int K(int i) {
        Integer num = null;
        Integer valueOf = (i < 2 || i > 4) ? null : Integer.valueOf(i - 1);
        if (valueOf != null && !P(3)) {
            if (valueOf.intValue() >= 2 && valueOf.intValue() <= 4) {
                num = Integer.valueOf(valueOf.intValue() - 1);
            }
            valueOf = num;
        }
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }

    public final float L(float f, int i, int i2) {
        float J2 = J(Integer.valueOf(i));
        float J3 = J(Integer.valueOf(i2));
        if (J2 == 0.0f && J3 == 0.0f) {
            return 0.0f;
        }
        return (f - J2) / (J3 - J2);
    }

    public boolean M() {
        return this.N <= 680.0f;
    }

    public boolean N() {
        return this.U > E();
    }

    public boolean O() {
        return this.U > 0.0f;
    }

    public abstract boolean P(int i);

    public final boolean Q(int i) {
        return P(i) && i != 0;
    }

    public void R() {
        K70 k70 = this.p0;
        if (k70 != null) {
            ((D70) k70).s(null);
        }
    }

    public void S(int i, int i2) {
        C5677xw xwVar = this.o0;
        if (xwVar != null) {
            xwVar.cancel();
        }
        U(J(Integer.valueOf(i)));
        T(i, i2);
        R();
    }

    public abstract void T(int i, int i2);

    public final void U(float f) {
        int B = B(f);
        float L2 = L(f, K(B), B);
        V(f);
        float f2 = 0.0f;
        if (B == 1 || B == 2) {
            C1796bA bAVar = (C1796bA) this;
            bAVar.e0 = 0.0f;
            bAVar.f0 = 1.0f;
            bAVar.Z = false;
            bAVar.b0 = 0.0f;
            bAVar.g0 = 0.0f;
            C3675mA r0 = bAVar.r0();
            if (r0.U) {
                r0.q(1.0f);
                r0.p();
            }
            Objects.requireNonNull(bAVar.o0());
            C4666rz s0 = bAVar.s0();
            Objects.requireNonNull(s0);
            if (L2 == 1.0f) {
                s0.b(0.0f);
            }
            if (L2 == 0.0f) {
                s0.e.o();
                C5006tz tzVar = s0.d;
                tzVar.b0 = false;
                tzVar.c0 = 0.0f;
                tzVar.a0 = false;
                s0.h.b(false);
            }
        } else {
            float f3 = 0.7f;
            if (B == 3) {
                C1796bA bAVar2 = (C1796bA) this;
                bAVar2.e0 = AbstractC4089od0.e(0.0f, bAVar2.f8254J, L2);
                bAVar2.f0 = AbstractC4089od0.e(1.0f, 0.7f, L2);
                bAVar2.Z = true;
                Math.min(L2, 0.5f);
                bAVar2.b0 = AbstractC4089od0.e(0.0f, 1.0f, Math.max(L2 - 0.5f, 0.0f) / 0.5f);
                float J2 = bAVar2.J(2);
                float f4 = 10.0f / bAVar2.F;
                bAVar2.g0 = AbstractC4089od0.e(0.0f, 1.0f, Math.min(bAVar2.U - J2, f4) / f4);
                C3675mA r02 = bAVar2.r0();
                if (r02.U) {
                    r02.q(1.0f);
                    if (L2 == 1.0f) {
                        AbstractC0292Et0 et0 = r02.Q;
                        float round = (float) Math.round((et0.E() + et0.T) * r02.S);
                        View view = r02.L;
                        if (view != null && r02.U && ((!r02.Z || r02.a0 != round) && r02.X != 0.0f)) {
                            float f5 = r02.Q.S * r02.S;
                            if (LocalizationUtils.isLayoutRtl()) {
                                f5 = -f5;
                            }
                            view.setTranslationX(f5);
                            view.setTranslationY(round);
                            view.setVisibility(0);
                            view.requestLayout();
                            r02.Z = true;
                            r02.a0 = round;
                            r02.b0 = true;
                        }
                    } else {
                        r02.p();
                    }
                }
                Objects.requireNonNull(bAVar2.o0());
                bAVar2.s0().b(L2);
            } else if (B == 4) {
                C1796bA bAVar3 = (C1796bA) this;
                boolean P2 = bAVar3.P(3);
                if (P2) {
                    f2 = bAVar3.f8254J;
                }
                bAVar3.e0 = AbstractC4089od0.e(f2, bAVar3.f8254J, L2);
                if (!P2) {
                    f3 = 1.0f;
                }
                bAVar3.f0 = AbstractC4089od0.e(f3, 0.4f, L2);
                bAVar3.Z = true;
                bAVar3.b0 = 1.0f;
                bAVar3.g0 = 1.0f;
                C3675mA r03 = bAVar3.r0();
                if (r03.U) {
                    r03.q(1.0f - L2);
                    r03.p();
                }
                Objects.requireNonNull(bAVar3.o0());
            }
        }
        C1796bA bAVar4 = (C1796bA) this;
        float f6 = (1.0f - bAVar4.f0) / 0.6f;
        if (((double) f6) == 0.0d) {
            GP0 gp0 = bAVar4.L0;
            if (gp0 != null) {
                gp0.b.a(false);
            }
            bAVar4.M0 = null;
            bAVar4.L0 = null;
            return;
        }
        bAVar4.L0 = ((ContextualSearchManager) bAVar4.I0).O;
        if (bAVar4.M0 == null) {
            Map c = UH0.c(MP0.l);
            NH0 nh0 = MP0.f8474a;
            JH0 jh0 = new JH0(null);
            jh0.f8282a = 0;
            HashMap hashMap = (HashMap) c;
            hashMap.put(nh0, jh0);
            MH0 mh0 = MP0.b;
            GH0 gh0 = new GH0(null);
            gh0.f8081a = true;
            hashMap.put(mh0, gh0);
            OH0 oh0 = MP0.c;
            CompositorViewHolder compositorViewHolder = bAVar4.s0.I0;
            LH0 lh0 = new LH0(null);
            lh0.f8415a = compositorViewHolder;
            hashMap.put(oh0, lh0);
            MH0 mh02 = MP0.d;
            GH0 gh02 = new GH0(null);
            gh02.f8081a = false;
            hashMap.put(mh02, gh02);
            OH0 oh02 = MP0.e;
            LH0 lh02 = new LH0(null);
            lh02.f8415a = null;
            hashMap.put(oh02, lh02);
            OH0 oh03 = MP0.f;
            LH0 lh03 = new LH0(null);
            lh03.f8415a = null;
            UH0 o = AbstractC2531fV.o(hashMap, oh03, lh03, c, null);
            bAVar4.M0 = o;
            bAVar4.L0.a(o);
        }
        LP0 lp0 = bAVar4.L0.b;
        Animator animator = lp0.e;
        if (animator != null) {
            animator.cancel();
        }
        lp0.c(f6);
    }

    public final void V(float f) {
        float f2;
        this.U = f;
        if (M()) {
            f2 = 0.0f;
        } else {
            f2 = (float) Math.round((this.N - (M() ? this.N : 600.0f)) / 2.0f);
        }
        this.S = f2;
        this.T = this.O - this.U;
        this.V = f == J(4);
    }

    public void w(Integer num, int i, long j) {
        this.m0 = num;
        this.n0 = i;
        float J2 = J(num);
        if (j > 0) {
            C5677xw xwVar = this.o0;
            if (xwVar != null) {
                xwVar.cancel();
            }
            C5677xw c = C5677xw.c(D(), this.U, J2, j, null);
            this.o0 = c;
            c.I.add(new C0353Ft0(this));
            C5677xw xwVar2 = this.o0;
            xwVar2.H.b(new C0475Ht0(this));
            this.o0.start();
        }
    }

    public final long x(float f, float f2) {
        return AbstractC4089od0.d((long) Math.round(Math.abs((f2 * 2000.0f) / f)), 116, 350);
    }

    public final float y() {
        float f;
        if (!M()) {
            return 0.0f;
        }
        C1796bA bAVar = (C1796bA) this;
        float f2 = bAVar.N0;
        if (f2 > 0.0f) {
            f = (bAVar.P * bAVar.F) + ((bAVar.O - bAVar.F()) / 2.0f) + (-(f2 * bAVar.F));
        } else {
            f = 0.0f;
        }
        return Math.max(Math.min(f - (this.P * this.F), 0.0f), -F());
    }

    public abstract void z(int i, boolean z);
}
