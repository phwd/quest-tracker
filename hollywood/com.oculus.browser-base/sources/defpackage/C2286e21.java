package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: e21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2286e21 implements ValueAnimator.AnimatorUpdateListener {
    public final View$OnLayoutChangeListenerC4337q21 F;

    public C2286e21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.F = q21;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View$OnLayoutChangeListenerC4337q21 q21 = this.F;
        Objects.requireNonNull(q21);
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        q21.F.l(AbstractC4507r21.e, intValue);
        q21.a(intValue);
    }
}
