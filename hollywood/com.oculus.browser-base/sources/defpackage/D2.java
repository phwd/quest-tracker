package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: D2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D2 extends AnimatorListenerAdapter {
    public final /* synthetic */ G2 F;

    public D2(G2 g2) {
        this.F = g2;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.c = null;
    }
}
