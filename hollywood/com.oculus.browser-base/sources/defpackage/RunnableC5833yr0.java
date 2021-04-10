package defpackage;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;

/* renamed from: yr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5833yr0 implements Runnable {
    public final C6003zr0 F;

    public RunnableC5833yr0(C6003zr0 zr0) {
        this.F = zr0;
    }

    public void run() {
        Z11 z11 = this.F.b;
        if (z11.c) {
            z11.c = false;
            View$OnLayoutChangeListenerC4337q21 q21 = z11.f9314a;
            ValueAnimator ofInt = ValueAnimator.ofInt(q21.F.f(AbstractC4507r21.e), ((Integer) q21.I.get()).intValue());
            ofInt.setEvaluator(new ArgbEvaluator());
            animation.InterpolatorC2176dO dOVar = G30.c;
            ofInt.setInterpolator(dOVar);
            ofInt.setDuration(200L);
            ofInt.addUpdateListener(new C2628g21(q21));
            ofInt.addListener(new C3824n21(q21));
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setInterpolator(dOVar);
            ofFloat.setDuration(150L);
            ofFloat.addUpdateListener(new C2799h21(q21));
            AnimatorSet animatorSet = new AnimatorSet();
            q21.S = animatorSet;
            animatorSet.play(ofInt).with(ofFloat);
            q21.S.addListener(new C4166p21(q21));
            q21.S.start();
        }
    }
}
