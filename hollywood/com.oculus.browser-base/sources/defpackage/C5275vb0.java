package defpackage;

import android.animation.ValueAnimator;

/* renamed from: vb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5275vb0 implements ValueAnimator.AnimatorUpdateListener {
    public final C5445wb0 F;

    public C5275vb0(C5445wb0 wb0) {
        this.F = wb0;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        C5445wb0 wb0 = this.F;
        float f = wb0.f;
        wb0.d = (valueAnimator.getAnimatedFraction() * (wb0.h - f)) + f;
        float f2 = wb0.g;
        float animatedFraction = (valueAnimator.getAnimatedFraction() * (wb0.i - f2)) + f2;
        wb0.e = animatedFraction;
        wb0.f11552a.a(wb0.d, animatedFraction);
    }
}
