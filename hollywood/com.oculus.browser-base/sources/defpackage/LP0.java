package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import java.util.ArrayList;
import java.util.Collection;
import org.chromium.base.Callback;

/* renamed from: LP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LP0 implements FP0 {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f8417a;
    public EP0 b;
    public ValueAnimator c;
    public ValueAnimator d;
    public Animator e;
    public UH0 f;
    public boolean g;
    public boolean h;
    public boolean i;

    public LP0(Runnable runnable, EP0 ep0) {
        this.f8417a = runnable;
        this.b = ep0;
    }

    public void a(boolean z) {
        if (this.i) {
            Animator animator = this.e;
            if (animator != null && !z) {
                animator.end();
                return;
            }
            return;
        }
        if (this.d == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            this.d = ofFloat;
            ofFloat.setDuration((long) 300);
            this.d.setInterpolator(animation.InterpolatorC5286vf.f);
            this.d.addListener(new KP0(this));
            this.d.addUpdateListener(new IP0(this));
        }
        this.i = true;
        this.d.setFloatValues(this.f.e(MP0.g), 0.0f);
        b(this.d);
        if (!z) {
            this.d.end();
        }
    }

    public final void b(Animator animator) {
        Animator animator2 = this.e;
        if (animator2 != animator || !animator2.isRunning()) {
            Animator animator3 = this.e;
            if (animator3 != null) {
                animator3.cancel();
            }
            this.e = animator;
            animator.start();
        }
    }

    public final void c(float f2) {
        EP0 ep0;
        EP0 ep02;
        UH0 uh0 = this.f;
        if (uh0 != null) {
            RH0 rh0 = MP0.g;
            if (!AbstractC4089od0.a(f2, uh0.e(rh0))) {
                this.f.k(rh0, f2);
                if (this.f.h(MP0.b) && (ep02 = this.b) != null) {
                    ep02.a(f2);
                }
                Collection a2 = this.f.a();
                QH0 qh0 = MP0.k;
                if (((ArrayList) a2).contains(qh0) && this.f.h(qh0) && (ep0 = this.b) != null) {
                    ep0.b(f2);
                }
                boolean z = f2 > 0.0f;
                UH0 uh02 = this.f;
                OH0 oh0 = MP0.e;
                if (!(uh02.g(oh0) == null || this.g == z)) {
                    ((Callback) this.f.g(oh0)).onResult(Boolean.valueOf(z));
                }
                this.g = z;
                if (this.i && !z && this.f != null) {
                    this.f = null;
                    this.f8417a.run();
                }
            }
        }
    }
}
