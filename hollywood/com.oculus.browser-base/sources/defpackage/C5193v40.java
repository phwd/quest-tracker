package defpackage;

import android.animation.ValueAnimator;

/* renamed from: v40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5193v40 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C4000o40 F;

    public C5193v40(C4000o40 o40) {
        this.F = o40;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.R = valueAnimator.getAnimatedFraction();
    }
}
