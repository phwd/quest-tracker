package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: s41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4684s41 extends AnimatorListenerAdapter {
    public final /* synthetic */ AbstractC4854t41 F;

    public C4684s41(AbstractC4854t41 t41) {
        this.F = t41;
    }

    public void onAnimationEnd(Animator animator) {
        AbstractC4854t41 t41 = this.F;
        t41.I = 0;
        t41.f11321J = null;
        t41.N = false;
    }
}
