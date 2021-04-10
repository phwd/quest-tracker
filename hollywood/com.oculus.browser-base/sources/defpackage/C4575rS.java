package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: rS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4575rS extends AnimatorListenerAdapter {
    public final /* synthetic */ ViewGroup F;
    public final /* synthetic */ View G;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS H;
    public final /* synthetic */ DS I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C3089im f11201J;

    public C4575rS(ViewGroup viewGroup, View view, AbstractComponentCallbacksC3550lS lSVar, DS ds, C3089im imVar) {
        this.F = viewGroup;
        this.G = view;
        this.H = lSVar;
        this.I = ds;
        this.f11201J = imVar;
    }

    public void onAnimationEnd(Animator animator) {
        Animator animator2;
        this.F.endViewTransition(this.G);
        AbstractComponentCallbacksC3550lS lSVar = this.H;
        C3038iS iSVar = lSVar.n0;
        if (iSVar == null) {
            animator2 = null;
        } else {
            animator2 = iSVar.b;
        }
        lSVar.T0(null);
        if (animator2 != null && this.F.indexOfChild(this.G) < 0) {
            this.I.a(this.H, this.f11201J);
        }
    }
}
