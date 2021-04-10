package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: F2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F2 extends AnimatorListenerAdapter {
    public final /* synthetic */ G2 F;

    public F2(G2 g2) {
        this.F = g2;
    }

    public void onAnimationEnd(Animator animator) {
        G2 g2 = this.F;
        g2.c = null;
        g2.g.c.setVisibility(8);
    }
}
