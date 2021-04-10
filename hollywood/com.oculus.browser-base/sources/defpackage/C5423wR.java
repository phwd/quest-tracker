package defpackage;

import android.animation.ValueAnimator;

/* renamed from: wR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5423wR implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ C5763yR F;

    public C5423wR(C5763yR yRVar) {
        this.F = yRVar;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.f11680a.setBottom(((Integer) valueAnimator.getAnimatedValue()).intValue());
        this.F.b();
    }
}
