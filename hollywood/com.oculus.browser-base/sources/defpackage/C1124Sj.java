package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: Sj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1124Sj implements ValueAnimator.AnimatorUpdateListener {
    public final C1551Zj F;
    public final int G;

    public C1124Sj(C1551Zj zj, int i) {
        this.F = zj;
        this.G = i;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        C1551Zj zj = this.F;
        int i = this.G;
        Objects.requireNonNull(zj);
        zj.k(false, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 0, i, zj.N, zj.P);
    }
}
