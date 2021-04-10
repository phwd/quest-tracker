package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: A01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A01 extends AnimatorListenerAdapter {
    public final /* synthetic */ C01 F;

    public A01(C01 c01) {
        this.F = c01;
    }

    public void onAnimationEnd(Animator animator) {
        C01 c01 = this.F;
        c01.V = null;
        ((M01) c01.Z).q(true);
        C01.X(this.F, true);
    }
}
