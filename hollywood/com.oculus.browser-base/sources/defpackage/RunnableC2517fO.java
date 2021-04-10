package defpackage;

import android.animation.ValueAnimator;

/* renamed from: fO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2517fO implements Runnable {
    public final /* synthetic */ C3200jO F;

    public RunnableC2517fO(C3200jO jOVar) {
        this.F = jOVar;
    }

    public void run() {
        C3200jO jOVar = this.F;
        int i = jOVar.C;
        if (i == 1) {
            jOVar.B.cancel();
        } else if (i != 2) {
            return;
        }
        jOVar.C = 3;
        ValueAnimator valueAnimator = jOVar.B;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        jOVar.B.setDuration((long) 500);
        jOVar.B.start();
    }
}
