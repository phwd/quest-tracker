package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.Objects;

/* renamed from: PD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PD extends AnimatorListenerAdapter {
    public final /* synthetic */ XK0 F;
    public final /* synthetic */ View G;
    public final /* synthetic */ ViewPropertyAnimator H;
    public final /* synthetic */ VD I;

    public PD(VD vd, XK0 xk0, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.I = vd;
        this.F = xk0;
        this.G = view;
        this.H = viewPropertyAnimator;
    }

    public void onAnimationCancel(Animator animator) {
        this.G.setAlpha(1.0f);
    }

    public void onAnimationEnd(Animator animator) {
        this.H.setListener(null);
        this.I.d(this.F);
        this.I.p.remove(this.F);
        this.I.l();
    }

    public void onAnimationStart(Animator animator) {
        Objects.requireNonNull(this.I);
    }
}
