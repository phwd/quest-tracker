package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;

/* renamed from: Ga0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0367Ga0 extends AnimatorListenerAdapter {
    public final /* synthetic */ LocationBarTablet F;

    public C0367Ga0(LocationBarTablet locationBarTablet) {
        this.F = locationBarTablet;
    }

    public void onAnimationEnd(Animator animator) {
        LocationBarTablet locationBarTablet = this.F;
        if (locationBarTablet.t0 == 1.0f) {
            locationBarTablet.s0 = false;
            LocationBarTablet.y(locationBarTablet);
            LocationBarTablet locationBarTablet2 = this.F;
            locationBarTablet2.p0 = false;
            locationBarTablet2.t();
        }
    }

    public void onAnimationStart(Animator animator) {
        this.F.s0 = true;
    }
}
