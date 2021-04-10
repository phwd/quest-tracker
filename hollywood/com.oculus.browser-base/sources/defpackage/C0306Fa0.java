package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.omnibox.LocationBarTablet;

/* renamed from: Fa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0306Fa0 extends AnimatorListenerAdapter {
    public final /* synthetic */ LocationBarTablet F;

    public C0306Fa0(LocationBarTablet locationBarTablet) {
        this.F = locationBarTablet;
    }

    public void onAnimationEnd(Animator animator) {
        LocationBarTablet locationBarTablet = this.F;
        if (locationBarTablet.t0 == 0.0f) {
            locationBarTablet.s0 = false;
            LocationBarTablet.y(locationBarTablet);
        }
    }

    public void onAnimationStart(Animator animator) {
        LocationBarTablet locationBarTablet = this.F;
        locationBarTablet.s0 = true;
        locationBarTablet.p0 = true;
        locationBarTablet.t();
    }
}
