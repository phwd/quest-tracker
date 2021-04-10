package defpackage;

import android.view.animation.Animation;

/* renamed from: D41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D41 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ K41 f7862a;

    public D41(K41 k41) {
        this.f7862a = k41;
    }

    public void onAnimationEnd(Animation animation) {
        J41 j41;
        K41 k41 = this.f7862a;
        if (k41.I) {
            k41.T.setAlpha(255);
            this.f7862a.T.start();
            K41 k412 = this.f7862a;
            if (k412.b0 && (j41 = k412.G) != null) {
                j41.a();
            }
        } else {
            k41.e();
        }
        K41 k413 = this.f7862a;
        k413.L = k413.P.getTop();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
