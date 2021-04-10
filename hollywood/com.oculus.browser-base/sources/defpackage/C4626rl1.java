package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: rl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4626rl1 extends AnimatorListenerAdapter {
    public final /* synthetic */ C4966tl1 F;

    public C4626rl1(C4966tl1 tl1) {
        this.F = tl1;
    }

    public void onAnimationEnd(Animator animator) {
        C4966tl1 tl1 = this.F;
        if (!tl1.L) {
            tl1.I.setStartDelay(1000);
            this.F.c();
            C4966tl1 tl12 = this.F;
            if (animator == tl12.f11368J) {
                tl12.I.start();
            }
        }
    }
}
