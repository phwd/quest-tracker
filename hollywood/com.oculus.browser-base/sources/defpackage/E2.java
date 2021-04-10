package defpackage;

import android.animation.ValueAnimator;

/* renamed from: E2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ G2 F;

    public E2(G2 g2) {
        this.F = g2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        G2 g2 = this.F;
        if (g2.g.f10460a != null) {
            valueAnimator.setIntValues((int) Math.max(0.0f, ((float) g2.a()) - this.F.e));
        }
    }
}
