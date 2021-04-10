package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.Objects;

/* renamed from: QD  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QD extends AnimatorListenerAdapter {
    public final /* synthetic */ XK0 F;
    public final /* synthetic */ int G;
    public final /* synthetic */ View H;
    public final /* synthetic */ int I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f8744J;
    public final /* synthetic */ VD K;

    public QD(VD vd, XK0 xk0, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
        this.K = vd;
        this.F = xk0;
        this.G = i;
        this.H = view;
        this.I = i2;
        this.f8744J = viewPropertyAnimator;
    }

    public void onAnimationCancel(Animator animator) {
        if (this.G != 0) {
            this.H.setTranslationX(0.0f);
        }
        if (this.I != 0) {
            this.H.setTranslationY(0.0f);
        }
    }

    public void onAnimationEnd(Animator animator) {
        this.f8744J.setListener(null);
        this.K.d(this.F);
        this.K.q.remove(this.F);
        this.K.l();
    }

    public void onAnimationStart(Animator animator) {
        Objects.requireNonNull(this.K);
    }
}
