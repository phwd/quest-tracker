package defpackage;

import android.animation.ValueAnimator;

/* renamed from: iO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3030iO implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C3200jO F;

    public C3030iO(C3200jO jOVar) {
        this.F = jOVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        this.F.e.setAlpha(floatValue);
        this.F.f.setAlpha(floatValue);
        this.F.u.invalidate();
    }
}
