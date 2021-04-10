package defpackage;

import android.view.animation.Animation;

/* renamed from: cd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class animation.Animation$AnimationListenerC2039cd0 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2380ed0 f9618a;
    public final /* synthetic */ C2551fd0 b;

    public animation.Animation$AnimationListenerC2039cd0(C2551fd0 fd0, C2380ed0 ed0) {
        this.b = fd0;
        this.f9618a = ed0;
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
        C2380ed0 ed0 = this.f9618a;
        ed0.l = ed0.e;
        ed0.m = ed0.f;
        ed0.n = ed0.g;
        ed0.c((ed0.k + 1) % ed0.j.length);
        C2380ed0 ed02 = this.f9618a;
        ed02.e = ed02.f;
        ed02.a();
        C2551fd0 fd0 = this.b;
        if (fd0.R) {
            fd0.R = false;
            animation.setDuration(1332);
            this.f9618a.d(false);
            return;
        }
        fd0.O = (fd0.O + 1.0f) % 5.0f;
    }

    public void onAnimationStart(Animation animation) {
        this.b.O = 0.0f;
    }
}
