package defpackage;

import android.animation.Animator;

/* renamed from: yu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5841yu implements Animator.AnimatorListener {
    public final /* synthetic */ C6011zu F;
    public final /* synthetic */ C0049Au G;

    public C5841yu(C0049Au au, C6011zu zuVar) {
        this.G = au;
        this.F = zuVar;
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
        this.G.a(1.0f, this.F, true);
        C6011zu zuVar = this.F;
        zuVar.k = zuVar.e;
        zuVar.l = zuVar.f;
        zuVar.m = zuVar.g;
        zuVar.a((zuVar.j + 1) % zuVar.i.length);
        C0049Au au = this.G;
        if (au.N) {
            au.N = false;
            animator.cancel();
            animator.setDuration(1332);
            animator.start();
            C6011zu zuVar2 = this.F;
            if (zuVar2.n) {
                zuVar2.n = false;
                return;
            }
            return;
        }
        au.M += 1.0f;
    }

    public void onAnimationStart(Animator animator) {
        this.G.M = 0.0f;
    }
}
