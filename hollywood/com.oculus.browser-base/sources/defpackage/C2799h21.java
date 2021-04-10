package defpackage;

import android.animation.ValueAnimator;

/* renamed from: h21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2799h21 implements ValueAnimator.AnimatorUpdateListener {
    public final View$OnLayoutChangeListenerC4337q21 F;

    public C2799h21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.F = q21;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.F.k(AbstractC4507r21.f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
