package defpackage;

import android.animation.ValueAnimator;

/* renamed from: bv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1921bv implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C2092cv F;

    public C1921bv(C2092cv cvVar) {
        this.F = cvVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.F.c.setScaleX(floatValue);
        this.F.c.setScaleY(floatValue);
    }
}
