package defpackage;

import android.view.ViewGroup;
import android.view.animation.Animation;

/* renamed from: qS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class animation.Animation$AnimationListenerC4405qS implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewGroup f11141a;
    public final /* synthetic */ AbstractComponentCallbacksC3550lS b;
    public final /* synthetic */ DS c;
    public final /* synthetic */ C3089im d;

    public animation.Animation$AnimationListenerC4405qS(ViewGroup viewGroup, AbstractComponentCallbacksC3550lS lSVar, DS ds, C3089im imVar) {
        this.f11141a = viewGroup;
        this.b = lSVar;
        this.c = ds;
        this.d = imVar;
    }

    public void onAnimationEnd(Animation animation) {
        this.f11141a.post(new RunnableC4234pS(this));
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
