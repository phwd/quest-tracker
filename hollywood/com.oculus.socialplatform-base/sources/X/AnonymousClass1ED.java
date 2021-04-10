package X;

import android.animation.ValueAnimator;

/* renamed from: X.1ED  reason: invalid class name */
public class AnonymousClass1ED implements Runnable {
    public static final String __redex_internal_original_name = "androidx.recyclerview.widget.FastScroller$1";
    public final /* synthetic */ AnonymousClass1EA A00;

    public AnonymousClass1ED(AnonymousClass1EA r1) {
        this.A00 = r1;
    }

    public final void run() {
        AnonymousClass1EA r2 = this.A00;
        int i = r2.A02;
        if (i == 1) {
            r2.A0K.cancel();
        } else if (i != 2) {
            return;
        }
        r2.A02 = 3;
        ValueAnimator valueAnimator = r2.A0K;
        valueAnimator.setFloatValues(((Number) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        valueAnimator.setDuration((long) 500);
        valueAnimator.start();
    }
}
