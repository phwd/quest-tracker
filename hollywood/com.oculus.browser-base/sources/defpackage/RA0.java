package defpackage;

import android.animation.ValueAnimator;

/* renamed from: RA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RA0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SA0 F;

    public RA0(SA0 sa0) {
        this.F = sa0;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        SA0 sa0 = this.F;
        float f = ((float) sa0.G) * floatValue;
        sa0.H.P.setTranslationY(f);
        if (sa0.F) {
            sa0.H.W.setTranslationY(-f);
            sa0.H.T.setBottom(Math.min(sa0.H.T.getMeasuredHeight() + sa0.H.T.getTop(), sa0.H.W.getTop()));
        }
    }
}
