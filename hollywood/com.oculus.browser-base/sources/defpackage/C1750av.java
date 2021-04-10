package defpackage;

import android.animation.ValueAnimator;

/* renamed from: av  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1750av implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C2092cv F;

    public C1750av(C2092cv cvVar) {
        this.F = cvVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
