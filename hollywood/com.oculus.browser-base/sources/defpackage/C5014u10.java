package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: u10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5014u10 extends AnimatorListenerAdapter {
    public final /* synthetic */ AbstractC5354w10 F;

    public C5014u10(AbstractC5354w10 w10) {
        this.F = w10;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.d();
        AbstractC5354w10 w10 = this.F;
        C5694y10 y10 = w10.b;
        y10.K = null;
        y10.f11655J.b(w10.c());
        this.F.b.e();
    }
}
