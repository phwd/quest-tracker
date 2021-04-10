package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

/* renamed from: QA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QA0 extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
    public final /* synthetic */ TA0 F;

    public QA0(TA0 ta0, HA0 ha0) {
        this.F = ta0;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.t0 = null;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.P.removeOnLayoutChangeListener(this);
        TA0 ta0 = this.F;
        ta0.t0 = ObjectAnimator.ofFloat(ta0.P, View.TRANSLATION_Y, (float) ta0.S, 0.0f);
        this.F.t0.setDuration(225);
        this.F.t0.setInterpolator(G30.e);
        this.F.t0.addListener(this);
        this.F.t0.start();
    }
}
