package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: SA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SA0 extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
    public final boolean F;
    public int G;
    public final /* synthetic */ TA0 H;

    public SA0(TA0 ta0, boolean z) {
        this.H = ta0;
        this.F = z;
    }

    public void onAnimationEnd(Animator animator) {
        this.H.P.setTranslationY(0.0f);
        this.H.W.setTranslationY(0.0f);
        this.H.P.requestLayout();
        TA0 ta0 = this.H;
        ta0.t0 = null;
        ta0.G.a();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Animator animator = this.H.t0;
        if (animator != null) {
            animator.cancel();
        }
        this.H.P.removeOnLayoutChangeListener(this);
        this.G = (i4 - i2) - (i8 - i6);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.addUpdateListener(new RA0(this));
        this.H.t0 = ofFloat;
        ofFloat.setDuration(225L);
        this.H.t0.setInterpolator(G30.e);
        this.H.t0.addListener(this);
        this.H.t0.start();
    }
}
