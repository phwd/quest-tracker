package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: A81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A81 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ C81 H;

    public A81(C81 c81, int i, int i2) {
        this.H = c81;
        this.F = i;
        this.G = i2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        C81 c81 = this.H;
        int i = c81.O;
        int i2 = this.F;
        TimeInterpolator timeInterpolator = P6.f8667a;
        int round = Math.round(((float) (i2 - i)) * animatedFraction) + i;
        int i3 = this.H.P;
        int round2 = Math.round(animatedFraction * ((float) (this.G - i3))) + i3;
        if (round != c81.L || round2 != c81.M) {
            c81.L = round;
            c81.M = round2;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            c81.postInvalidateOnAnimation();
        }
    }
}
