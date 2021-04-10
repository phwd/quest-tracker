package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: xR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5593xR extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ C5763yR G;

    public C5593xR(C5763yR yRVar, Runnable runnable) {
        this.G = yRVar;
        this.F = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        C5763yR yRVar = this.G;
        Runnable runnable = this.F;
        yRVar.b();
        runnable.run();
        this.G.f11680a.requestLayout();
    }
}
