package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: A7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A7 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C3234jd0 F;

    public A7(AppBarLayout appBarLayout, C3234jd0 jd0) {
        this.F = jd0;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.n(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
