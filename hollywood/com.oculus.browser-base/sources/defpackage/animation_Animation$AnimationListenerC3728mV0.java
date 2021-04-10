package defpackage;

import android.view.animation.Animation;

/* renamed from: mV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class animation.Animation$AnimationListenerC3728mV0 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4241pV0 f10426a;

    public animation.Animation$AnimationListenerC3728mV0(C4241pV0 pv0) {
        this.f10426a = pv0;
    }

    public void onAnimationEnd(Animation animation) {
        this.f10426a.R.a(false, false);
        this.f10426a.R.setVisibility(4);
        C4241pV0 pv0 = this.f10426a;
        if (!pv0.N) {
            pv0.c();
        }
        this.f10426a.b();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
