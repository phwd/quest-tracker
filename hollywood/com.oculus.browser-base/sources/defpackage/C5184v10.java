package defpackage;

import android.animation.ValueAnimator;

/* renamed from: v10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5184v10 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ D10 F;
    public final /* synthetic */ AbstractC5354w10 G;

    public C5184v10(AbstractC5354w10 w10, D10 d10) {
        this.G = w10;
        this.F = d10;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
        this.G.b.L.a();
    }
}
