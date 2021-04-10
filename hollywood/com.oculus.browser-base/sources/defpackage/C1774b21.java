package defpackage;

import android.animation.ValueAnimator;
import java.util.Iterator;

/* renamed from: b21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1774b21 implements ValueAnimator.AnimatorUpdateListener {
    public final View$OnLayoutChangeListenerC4337q21 F;

    public C1774b21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.F = q21;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Iterator it = this.F.H.iterator();
        while (it.hasNext()) {
            ((Y11) it.next()).f(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }
}
