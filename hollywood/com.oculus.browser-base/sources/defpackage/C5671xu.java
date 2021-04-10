package defpackage;

import android.animation.ValueAnimator;

/* renamed from: xu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5671xu implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C6011zu F;
    public final /* synthetic */ C0049Au G;

    public C5671xu(C0049Au au, C6011zu zuVar) {
        this.G = au;
        this.F = zuVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.G.c(floatValue, this.F);
        this.G.a(floatValue, this.F, false);
        this.G.invalidateSelf();
    }
}
