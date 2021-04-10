package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: Bg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0081Bg1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TextInputLayout F;

    public C0081Bg1(TextInputLayout textInputLayout) {
        this.F = textInputLayout;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.F.g1.n(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
