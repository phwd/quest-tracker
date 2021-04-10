package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import org.chromium.base.task.PostTask;

/* renamed from: K41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K41 extends ViewGroup {
    public static final int[] F = {16842766};
    public J41 G;
    public C5704y41 H;
    public boolean I = false;

    /* renamed from: J  reason: collision with root package name */
    public float f8340J = -1.0f;
    public int K = getResources().getInteger(17694721);
    public int L;
    public boolean M = false;
    public boolean N;
    public final DecelerateInterpolator O;
    public C5501wu P;
    public int Q = -1;
    public int R;
    public int S;
    public C2551fd0 T;
    public Animation U;
    public Animation V;
    public Animation.AnimationListener W;
    public float a0;
    public boolean b0;
    public int c0;
    public int d0;
    public Animation.AnimationListener e0 = new D41(this);
    public float f0;
    public final Animation g0 = new H41(this);
    public final Animation h0 = new I41(this);

    public K41(Context context) {
        super(context, null);
        setWillNotDraw(false);
        this.O = new DecelerateInterpolator(2.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, F);
        setEnabled(obtainStyledAttributes.getBoolean(0, true));
        obtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.density * 40.0f);
        this.c0 = i;
        this.d0 = i;
        this.P = new C5501wu(getContext(), -328966, 20.0f);
        C2551fd0 fd0 = new C2551fd0(getContext(), this);
        this.T = fd0;
        fd0.f9935J.w = -328966;
        this.P.setImageDrawable(fd0);
        this.P.setVisibility(8);
        addView(this.P);
        setChildrenDrawingOrderEnabled(true);
        float f = displayMetrics.density * 64.0f;
        this.a0 = f;
        this.f8340J = f;
    }

    public static void a(K41 k41, float f) {
        int i = k41.R;
        k41.j((i + ((int) (((float) (k41.S - i)) * f))) - k41.P.getTop(), false);
    }

    public static void b(K41 k41, float f) {
        k41.P.setScaleX(f);
        k41.P.setScaleY(f);
    }

    public void c(float f) {
        if (isEnabled() && this.N) {
            float f2 = this.f8340J / ((float) 3);
            float max = this.f0 + Math.max(-f2, Math.min(f2, f * 0.5f));
            this.f0 = max;
            C2380ed0 ed0 = this.T.f9935J;
            if (!ed0.o) {
                ed0.o = true;
                ed0.a();
            }
            float f3 = max / this.f8340J;
            if (f3 >= 0.0f) {
                float min = Math.min(1.0f, Math.abs(f3));
                float max2 = (((float) Math.max(((double) min) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                float abs = Math.abs(max) - this.f8340J;
                float f4 = this.a0;
                double max3 = (double) (Math.max(0.0f, Math.min(abs, f4 * 2.0f) / f4) / 4.0f);
                float pow = ((float) (max3 - Math.pow(max3, 2.0d))) * 2.0f;
                int i = this.S + ((int) ((f4 * min) + (f4 * pow * 2.0f)));
                if (this.P.getVisibility() != 0) {
                    this.P.setVisibility(0);
                }
                this.P.setScaleX(1.0f);
                this.P.setScaleY(1.0f);
                C2551fd0 fd0 = this.T;
                float min2 = Math.min(0.8f, max2 * 0.8f);
                C2380ed0 ed02 = fd0.f9935J;
                ed02.e = 0.0f;
                ed02.a();
                C2380ed0 ed03 = fd0.f9935J;
                ed03.f = min2;
                ed03.a();
                C2551fd0 fd02 = this.T;
                float min3 = Math.min(1.0f, max2);
                C2380ed0 ed04 = fd02.f9935J;
                if (min3 != ed04.q) {
                    ed04.q = min3;
                    ed04.a();
                }
                this.T.setAlpha(((int) (Math.max(0.0f, Math.min(1.0f, (min - 0.9f) / 0.1f)) * 179.0f)) + 76);
                C2380ed0 ed05 = this.T.f9935J;
                ed05.g = ((pow * 2.0f) + ((max2 * 0.4f) - 16.0f)) * 0.5f;
                ed05.a();
                j(i - this.L, true);
            }
        }
    }

    public void d(boolean z) {
        if (this.N) {
            this.N = false;
            float f = this.f0;
            if (!isEnabled() || !z || f <= this.f8340J) {
                this.I = false;
                C2551fd0 fd0 = this.T;
                C2380ed0 ed0 = fd0.f9935J;
                ed0.e = 0.0f;
                ed0.a();
                C2380ed0 ed02 = fd0.f9935J;
                ed02.f = 0.0f;
                ed02.a();
                if (this.W == null) {
                    this.W = new G41(this);
                }
                Animation.AnimationListener animationListener = this.W;
                this.R = this.L;
                this.h0.reset();
                this.h0.setDuration(200);
                this.h0.setInterpolator(this.O);
                if (animationListener != null) {
                    this.P.F = animationListener;
                }
                this.P.clearAnimation();
                this.P.startAnimation(this.h0);
                C2380ed0 ed03 = this.T.f9935J;
                if (ed03.o) {
                    ed03.o = false;
                    ed03.a();
                    return;
                }
                return;
            }
            i(true, true);
        }
    }

    public void e() {
        this.N = false;
        i(false, false);
        this.T.stop();
        this.P.setVisibility(8);
        g(255);
        j(this.S - this.L, true);
        this.L = this.P.getTop();
        C5704y41 y41 = this.H;
        if (y41 != null) {
            C41 c41 = y41.f11660a;
            if (c41.M == null) {
                A41 a41 = new A41(c41);
                c41.M = a41;
                PostTask.b(Zo1.f9374a, a41, 0);
            }
        }
    }

    public void f(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        C2551fd0 fd0 = this.T;
        C2380ed0 ed0 = fd0.f9935J;
        ed0.j = iArr2;
        ed0.c(0);
        fd0.f9935J.c(0);
    }

    public final void g(int i) {
        this.P.getBackground().setAlpha(i);
        C2380ed0 ed0 = this.T.f9935J;
        if (ed0.u != i) {
            ed0.u = i;
            ed0.a();
        }
    }

    public int getChildDrawingOrder(int i, int i2) {
        int i3 = this.Q;
        if (i3 < 0) {
            return i2;
        }
        if (i2 == i - 1) {
            return i3;
        }
        return i2 >= i3 ? i2 + 1 : i2;
    }

    public void h(boolean z) {
        if (!z || this.I == z) {
            i(z, false);
            return;
        }
        this.I = z;
        j(((int) (this.a0 + ((float) this.S))) - this.L, true);
        this.b0 = false;
        Animation.AnimationListener animationListener = this.e0;
        this.P.setVisibility(0);
        this.T.setAlpha(255);
        if (this.U == null) {
            E41 e41 = new E41(this);
            this.U = e41;
            e41.setDuration((long) this.K);
        }
        if (animationListener != null) {
            this.P.F = animationListener;
        }
        this.P.clearAnimation();
        this.P.startAnimation(this.U);
    }

    public final void i(boolean z, boolean z2) {
        if (this.I != z) {
            this.b0 = z2;
            this.I = z;
            if (z) {
                int i = this.L;
                Animation.AnimationListener animationListener = this.e0;
                this.R = i;
                this.g0.reset();
                this.g0.setDuration(200);
                this.g0.setInterpolator(this.O);
                if (animationListener != null) {
                    this.P.F = animationListener;
                }
                this.P.clearAnimation();
                this.P.startAnimation(this.g0);
                return;
            }
            l(this.e0);
        }
    }

    public final void j(int i, boolean z) {
        this.P.bringToFront();
        this.P.offsetTopAndBottom(i);
        this.L = this.P.getTop();
    }

    public boolean k() {
        if (!isEnabled() || this.I) {
            return false;
        }
        this.P.clearAnimation();
        this.T.stop();
        j(this.S - this.P.getTop(), true);
        this.f0 = 0.0f;
        this.N = true;
        this.T.setAlpha(76);
        return true;
    }

    public final void l(Animation.AnimationListener animationListener) {
        if (this.V == null) {
            F41 f41 = new F41(this);
            this.V = f41;
            f41.setDuration(150);
        }
        C5501wu wuVar = this.P;
        wuVar.F = animationListener;
        wuVar.clearAnimation();
        this.P.startAnimation(this.V);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        if (getChildCount() != 0) {
            int measuredWidth2 = this.P.getMeasuredWidth();
            int measuredHeight = this.P.getMeasuredHeight();
            int i5 = measuredWidth / 2;
            int i6 = measuredWidth2 / 2;
            int i7 = this.L;
            this.P.layout(i5 - i6, i7, i5 + i6, measuredHeight + i7);
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.P.measure(View.MeasureSpec.makeMeasureSpec(this.c0, 1073741824), View.MeasureSpec.makeMeasureSpec(this.d0, 1073741824));
        if (!this.M) {
            this.M = true;
            int i3 = (int) (((float) (-this.P.getMeasuredHeight())) * 1.05f);
            this.S = i3;
            this.L = i3;
        }
        this.Q = -1;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == this.P) {
                this.Q = i4;
                return;
            }
        }
    }
}
