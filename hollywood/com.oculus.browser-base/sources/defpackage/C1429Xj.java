package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: Xj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1429Xj extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ C1551Zj G;

    public C1429Xj(C1551Zj zj, int i) {
        this.G = zj;
        this.F = i;
    }

    public void onAnimationCancel(Animator animator) {
        C1551Zj zj = this.G;
        zj.k(false, 0, 0, this.F, zj.N, zj.P);
    }

    public void onAnimationEnd(Animator animator) {
        this.G.b0 = null;
    }
}
