package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: Yj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1490Yj extends AnimatorListenerAdapter {
    public final /* synthetic */ C1551Zj F;

    public C1490Yj(C1551Zj zj) {
        this.F = zj;
    }

    public void onAnimationEnd(Animator animator) {
        C1551Zj zj = this.F;
        zj.k(false, 0, 0, zj.M, zj.N, zj.P);
        this.F.b0 = null;
    }
}
