package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: B01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B01 extends AnimatorListenerAdapter {
    public final /* synthetic */ C01 F;

    public B01(C01 c01) {
        this.F = c01;
    }

    public void onAnimationEnd(Animator animator) {
        C01 c01 = this.F;
        c01.V = null;
        c01.b0.j();
        c01.W = false;
        c01.h();
        C01.X(this.F, false);
    }
}
