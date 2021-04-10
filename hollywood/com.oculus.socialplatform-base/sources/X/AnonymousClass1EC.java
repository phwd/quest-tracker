package X;

import android.animation.ValueAnimator;

/* renamed from: X.1EC  reason: invalid class name */
public class AnonymousClass1EC implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AnonymousClass1EA A00;

    public AnonymousClass1EC(AnonymousClass1EA r1) {
        this.A00 = r1;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int floatValue = (int) (((Number) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
        AnonymousClass1EA r1 = this.A00;
        r1.A0O.setAlpha(floatValue);
        r1.A0M.setAlpha(floatValue);
        r1.A0A.invalidate();
    }
}
