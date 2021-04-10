package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: Tj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1185Tj implements ValueAnimator.AnimatorUpdateListener {
    public final C1551Zj F;
    public final int G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f8978J;

    public C1185Tj(C1551Zj zj, int i, int i2, int i3, int i4) {
        this.F = zj;
        this.G = i;
        this.H = i2;
        this.I = i3;
        this.f8978J = i4;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        C1551Zj zj = this.F;
        int i = this.G;
        int i2 = this.H;
        int i3 = this.I;
        int i4 = this.f8978J;
        Objects.requireNonNull(zj);
        float floatValue = (((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) (i4 - i3))) + ((float) i3);
        zj.k(false, (int) (floatValue - ((float) i4)), zj.b(), (int) floatValue, (int) ((((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) (i2 - i))) + ((float) i)), zj.V);
    }
}
