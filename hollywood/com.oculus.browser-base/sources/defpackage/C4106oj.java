package defpackage;

import android.animation.ValueAnimator;
import org.chromium.components.browser_ui.bottomsheet.BottomSheet;

/* renamed from: oj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4106oj implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ BottomSheet G;

    public C4106oj(BottomSheet bottomSheet, int i) {
        this.G = bottomSheet;
        this.F = i;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        BottomSheet bottomSheet = this.G;
        if (valueAnimator == bottomSheet.O) {
            bottomSheet.u(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.F, true);
        }
    }
}
