package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.Objects;

/* renamed from: OD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OD extends AnimatorListenerAdapter {
    public final /* synthetic */ XK0 F;
    public final /* synthetic */ ViewPropertyAnimator G;
    public final /* synthetic */ View H;
    public final /* synthetic */ VD I;

    public OD(VD vd, XK0 xk0, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.I = vd;
        this.F = xk0;
        this.G = viewPropertyAnimator;
        this.H = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.G.setListener(null);
        this.H.setAlpha(1.0f);
        this.I.d(this.F);
        this.I.r.remove(this.F);
        this.I.l();
    }

    public void onAnimationStart(Animator animator) {
        Objects.requireNonNull(this.I);
    }
}
