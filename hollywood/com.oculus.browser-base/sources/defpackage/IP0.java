package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: IP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class IP0 implements ValueAnimator.AnimatorUpdateListener {
    public final LP0 F;

    public IP0(LP0 lp0) {
        this.F = lp0;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        LP0 lp0 = this.F;
        Objects.requireNonNull(lp0);
        lp0.c(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
