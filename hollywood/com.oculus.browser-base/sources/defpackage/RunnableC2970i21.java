package defpackage;

import android.animation.ValueAnimator;
import java.util.Objects;

/* renamed from: i21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2970i21 implements Runnable {
    public final View$OnLayoutChangeListenerC4337q21 F;

    public RunnableC2970i21(View$OnLayoutChangeListenerC4337q21 q21) {
        this.F = q21;
    }

    public void run() {
        View$OnLayoutChangeListenerC4337q21 q21 = this.F;
        Objects.requireNonNull(q21);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        q21.Q = ofFloat;
        ofFloat.setInterpolator(G30.c);
        q21.Q.setDuration(150L);
        q21.Q.addUpdateListener(new C1945c21(q21));
        q21.Q.addListener(new C3311k21(q21));
        q21.Q.start();
    }
}
