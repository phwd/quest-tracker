package defpackage;

import android.view.animation.Animation;
import androidx.mediarouter.app.OverlayListView;

/* renamed from: Af0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class animation.Animation$AnimationListenerC0016Af0 implements Animation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC0504If0 f7685a;

    public animation.Animation$AnimationListenerC0016Af0(DialogC0504If0 if0) {
        this.f7685a = if0;
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        OverlayListView overlayListView = this.f7685a.i0;
        for (C0048At0 at0 : overlayListView.F) {
            if (!at0.k) {
                at0.j = overlayListView.getDrawingTime();
                at0.k = true;
            }
        }
        DialogC0504If0 if0 = this.f7685a;
        if0.i0.postDelayed(if0.T0, (long) if0.L0);
    }
}
