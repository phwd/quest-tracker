package defpackage;

import android.animation.ValueAnimator;

/* renamed from: sl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4796sl1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C4966tl1 F;

    public C4796sl1(C4966tl1 tl1, C4626rl1 rl1) {
        this.F = tl1;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        C4966tl1 tl1 = this.F;
        tl1.P = valueAnimator;
        tl1.O = valueAnimator.getAnimatedFraction();
        C4966tl1 tl12 = this.F;
        tl12.b(tl12.P, tl12.O);
    }
}
