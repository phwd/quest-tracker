package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;

/* renamed from: O  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O extends AnimatorListenerAdapter {
    public boolean F;
    public final /* synthetic */ AccessibilityTabModelListItem G;

    public O(AccessibilityTabModelListItem accessibilityTabModelListItem) {
        this.G = accessibilityTabModelListItem;
    }

    public void onAnimationCancel(Animator animator) {
        this.F = true;
        this.G.k0 = false;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.F) {
            AccessibilityTabModelListItem accessibilityTabModelListItem = this.G;
            int i = AccessibilityTabModelListItem.F;
            accessibilityTabModelListItem.f(false);
            this.G.setAlpha(1.0f);
            this.G.T.setAlpha(1.0f);
            this.G.b0.setAlpha(1.0f);
            this.G.b();
            AccessibilityTabModelListItem accessibilityTabModelListItem2 = this.G;
            K k = accessibilityTabModelListItem2.g0;
            int id = accessibilityTabModelListItem2.d0.getId();
            if (k.f8333a.H.t(id)) {
                k.f8333a.H.v(id);
            } else {
                AbstractC1160Ta1.a(k.f8333a.H, id, false);
            }
            k.f8333a.notifyDataSetChanged();
        }
    }

    public void onAnimationStart(Animator animator) {
        this.F = false;
    }
}
