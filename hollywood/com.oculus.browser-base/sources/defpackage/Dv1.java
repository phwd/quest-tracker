package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: Dv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dv1 extends AnimatorListenerAdapter implements AbstractC2753gn1 {
    public final View F;
    public final int G;
    public final ViewGroup H;
    public final boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f7924J;
    public boolean K = false;

    public Dv1(View view, int i, boolean z) {
        this.F = view;
        this.G = i;
        this.H = (ViewGroup) view.getParent();
        this.I = z;
        g(true);
    }

    @Override // defpackage.AbstractC2753gn1
    public void a(AbstractC2924hn1 hn1) {
    }

    @Override // defpackage.AbstractC2753gn1
    public void b(AbstractC2924hn1 hn1) {
        g(true);
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        f();
        hn1.w(this);
    }

    @Override // defpackage.AbstractC2753gn1
    public void d(AbstractC2924hn1 hn1) {
        g(false);
    }

    @Override // defpackage.AbstractC2753gn1
    public void e(AbstractC2924hn1 hn1) {
    }

    public final void f() {
        if (!this.K) {
            AbstractC4315pv1.f11100a.f(this.F, this.G);
            ViewGroup viewGroup = this.H;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        g(false);
    }

    public final void g(boolean z) {
        ViewGroup viewGroup;
        if (this.I && this.f7924J != z && (viewGroup = this.H) != null) {
            this.f7924J = z;
            AbstractC3286ju1.a(viewGroup, z);
        }
    }

    public void onAnimationCancel(Animator animator) {
        this.K = true;
    }

    public void onAnimationEnd(Animator animator) {
        f();
    }

    public void onAnimationPause(Animator animator) {
        if (!this.K) {
            AbstractC4315pv1.f11100a.f(this.F, this.G);
        }
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationResume(Animator animator) {
        if (!this.K) {
            AbstractC4315pv1.f11100a.f(this.F, 0);
        }
    }

    public void onAnimationStart(Animator animator) {
    }
}
