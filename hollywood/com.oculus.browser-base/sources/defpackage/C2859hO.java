package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: hO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2859hO extends AnimatorListenerAdapter {
    public boolean F = false;
    public final /* synthetic */ C3200jO G;

    public C2859hO(C3200jO jOVar) {
        this.G = jOVar;
    }

    public void onAnimationCancel(Animator animator) {
        this.F = true;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.F) {
            this.F = false;
        } else if (((Float) this.G.B.getAnimatedValue()).floatValue() == 0.0f) {
            C3200jO jOVar = this.G;
            jOVar.C = 0;
            jOVar.n(0);
        } else {
            C3200jO jOVar2 = this.G;
            jOVar2.C = 2;
            jOVar2.u.invalidate();
        }
    }
}
