package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: sp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4807sp0 extends AnimatorListenerAdapter {
    public final /* synthetic */ C5147up0 F;

    public C4807sp0(C5147up0 up0) {
        this.F = up0;
    }

    public void onAnimationEnd(Animator animator) {
        C5147up0 up0 = this.F;
        int round = Math.round(up0.r / ((float) up0.e));
        C5147up0 up02 = this.F;
        up0.r = (float) (round * up02.e);
        up02.p();
        this.F.E.q0();
    }
}
