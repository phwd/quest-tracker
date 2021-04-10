package defpackage;

import android.animation.ValueAnimator;

/* renamed from: zJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5917zJ implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AJ F;

    public C5917zJ(AJ aj) {
        this.F = aj;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
