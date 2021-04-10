package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: dn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2241dn1 extends AnimatorListenerAdapter {
    public final /* synthetic */ AbstractC2924hn1 F;

    public C2241dn1(AbstractC2924hn1 hn1) {
        this.F = hn1;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.n();
        animator.removeListener(this);
    }
}
