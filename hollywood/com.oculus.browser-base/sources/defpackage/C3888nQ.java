package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;

/* renamed from: nQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3888nQ extends AnimatorListenerAdapter {
    public final /* synthetic */ C4230pQ F;

    public C3888nQ(C4230pQ pQVar) {
        this.F = pQVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.F.getParent() != null) {
            ((ViewGroup) this.F.getParent()).removeView(this.F);
        }
    }
}
