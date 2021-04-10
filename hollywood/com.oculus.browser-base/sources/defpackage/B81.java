package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: B81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B81 extends AnimatorListenerAdapter {
    public final /* synthetic */ int F;
    public final /* synthetic */ C81 G;

    public B81(C81 c81, int i) {
        this.G = c81;
        this.F = i;
    }

    public void onAnimationEnd(Animator animator) {
        C81 c81 = this.G;
        c81.I = this.F;
        c81.f7788J = 0.0f;
    }

    public void onAnimationStart(Animator animator) {
        this.G.I = this.F;
    }
}
