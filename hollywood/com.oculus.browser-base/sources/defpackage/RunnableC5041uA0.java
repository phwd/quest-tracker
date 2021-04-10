package defpackage;

import android.view.animation.AlphaAnimation;

/* renamed from: uA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5041uA0 implements Runnable {
    public final /* synthetic */ C5211vA0 F;

    public RunnableC5041uA0(C5211vA0 va0) {
        this.F = va0;
    }

    public void run() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(this.F.a0.getAlpha(), 0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(G30.e);
        alphaAnimation.setFillAfter(true);
        this.F.a0.startAnimation(alphaAnimation);
    }
}
