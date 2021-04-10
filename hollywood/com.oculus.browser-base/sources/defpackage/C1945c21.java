package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: c21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1945c21 implements ValueAnimator.AnimatorUpdateListener {
    public final View$OnLayoutChangeListenerC4337q21 F;

    public C1945c21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.F = q21;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View$OnLayoutChangeListenerC4337q21 q21 = this.F;
        Objects.requireNonNull(q21);
        q21.F.k(AbstractC4507r21.f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
