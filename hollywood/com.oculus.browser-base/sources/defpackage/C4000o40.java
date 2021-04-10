package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: o40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4000o40 implements Animator.AnimatorListener {
    public final float F;
    public final float G;
    public final float H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public final XK0 f10530J;
    public final int K;
    public final ValueAnimator L;
    public boolean M;
    public float N;
    public float O;
    public boolean P = false;
    public boolean Q = false;
    public float R;
    public final /* synthetic */ int S;
    public final /* synthetic */ XK0 T;
    public final /* synthetic */ C5533x40 U;

    public C4000o40(C5533x40 x40, XK0 xk0, int i, int i2, float f, float f2, float f3, float f4, int i3, XK0 xk02) {
        this.U = x40;
        this.S = i3;
        this.T = xk02;
        this.K = i2;
        this.f10530J = xk0;
        this.F = f;
        this.G = f2;
        this.H = f3;
        this.I = f4;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.L = ofFloat;
        ofFloat.addUpdateListener(new C5193v40(this));
        ofFloat.setTarget(xk0.G);
        ofFloat.addListener(this);
        this.R = 0.0f;
    }

    public void onAnimationCancel(Animator animator) {
        this.R = 1.0f;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.Q) {
            this.f10530J.u(true);
        }
        this.Q = true;
        if (!this.P) {
            if (this.S <= 0) {
                C5533x40 x40 = this.U;
                x40.m.b(x40.r, this.T);
            } else {
                this.U.f11584a.add(this.T.G);
                this.M = true;
                int i = this.S;
                if (i > 0) {
                    C5533x40 x402 = this.U;
                    x402.r.post(new RunnableC4171p40(x402, this, i));
                }
            }
            C5533x40 x403 = this.U;
            View view = x403.w;
            View view2 = this.T.G;
            if (view == view2) {
                x403.s(view2);
            }
        }
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }
}
