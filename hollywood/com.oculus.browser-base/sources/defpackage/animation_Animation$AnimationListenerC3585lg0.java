package defpackage;

import android.view.animation.Animation;

/* renamed from: lg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class animation.Animation$AnimationListenerC3585lg0 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4950tg0 f10364a;

    public animation.Animation$AnimationListenerC3585lg0(C4950tg0 tg0) {
        this.f10364a = tg0;
    }

    public void onAnimationEnd(Animation animation) {
        DialogC5460wg0 wg0 = this.f10364a.R;
        wg0.b0 = false;
        wg0.o();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.f10364a.R.b0 = true;
    }
}
