package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: cn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2070cn1 extends AnimatorListenerAdapter {
    public final /* synthetic */ C4931ta F;
    public final /* synthetic */ AbstractC2924hn1 G;

    public C2070cn1(AbstractC2924hn1 hn1, C4931ta taVar) {
        this.G = hn1;
        this.F = taVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.remove(animator);
        this.G.U.remove(animator);
    }

    public void onAnimationStart(Animator animator) {
        this.G.U.add(animator);
    }
}
