package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.accessibility_tab_switcher.AccessibilityTabModelListItem;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: N  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N extends AnimatorListenerAdapter {
    public boolean F;
    public final /* synthetic */ AccessibilityTabModelListItem G;

    public N(AccessibilityTabModelListItem accessibilityTabModelListItem) {
        this.G = accessibilityTabModelListItem;
    }

    public void onAnimationCancel(Animator animator) {
        this.F = true;
        this.G.k0 = false;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.F) {
            AccessibilityTabModelListItem accessibilityTabModelListItem = this.G;
            K k = accessibilityTabModelListItem.g0;
            int id = accessibilityTabModelListItem.d0.getId();
            TabModel tabModel = k.f8333a.H;
            tabModel.q(AbstractC1160Ta1.d(tabModel, id), true, false, true);
            k.f8333a.notifyDataSetChanged();
            this.G.setTranslationX(0.0f);
            this.G.setScaleX(1.0f);
            this.G.setScaleY(1.0f);
            this.G.setAlpha(0.0f);
            this.G.f(true);
            this.G.d(false);
            AccessibilityTabModelListItem accessibilityTabModelListItem2 = this.G;
            accessibilityTabModelListItem2.m0.postDelayed(accessibilityTabModelListItem2.l0, (long) accessibilityTabModelListItem2.I);
        }
    }

    public void onAnimationStart(Animator animator) {
        this.F = false;
    }
}
