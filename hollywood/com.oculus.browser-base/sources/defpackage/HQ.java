package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.findinpage.FindToolbarTablet;

/* renamed from: HQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HQ extends AnimatorListenerAdapter {
    public final /* synthetic */ FindToolbarTablet F;

    public HQ(FindToolbarTablet findToolbarTablet) {
        this.F = findToolbarTablet;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.g0 = null;
    }

    public void onAnimationStart(Animator animator) {
        this.F.postInvalidateOnAnimation();
    }
}
