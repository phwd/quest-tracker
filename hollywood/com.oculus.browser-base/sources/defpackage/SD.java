package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.Objects;

/* renamed from: SD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SD extends AnimatorListenerAdapter {
    public final /* synthetic */ TD F;
    public final /* synthetic */ ViewPropertyAnimator G;
    public final /* synthetic */ View H;
    public final /* synthetic */ VD I;

    public SD(VD vd, TD td, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.I = vd;
        this.F = td;
        this.G = viewPropertyAnimator;
        this.H = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.setListener(null);
        this.H.setAlpha(1.0f);
        this.H.setTranslationX(0.0f);
        this.H.setTranslationY(0.0f);
        this.I.d(this.F.b);
        this.I.s.remove(this.F.b);
        this.I.l();
    }

    public void onAnimationStart(Animator animator) {
        VD vd = this.I;
        XK0 xk0 = this.F.b;
        Objects.requireNonNull(vd);
    }
}
