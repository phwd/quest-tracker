package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: GS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GS extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup F;
    public final /* synthetic */ View G;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS H;

    public GS(KS ks, ViewGroup viewGroup, View view, AbstractComponentCallbacksC3550lS lSVar) {
        this.F = viewGroup;
        this.G = view;
        this.H = lSVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.endViewTransition(this.G);
        animator.removeListener(this);
        AbstractComponentCallbacksC3550lS lSVar = this.H;
        View view = lSVar.k0;
        if (view != null && lSVar.d0) {
            view.setVisibility(8);
        }
    }
}
