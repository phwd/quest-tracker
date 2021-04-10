package defpackage;

import android.animation.Animator;
import org.chromium.chrome.browser.toolbar.IncognitoToggleTabLayout;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTTPhone;

/* renamed from: qd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4432qd1 extends AbstractC2406em {
    public final /* synthetic */ boolean G;
    public final /* synthetic */ TabSwitcherModeTTPhone H;

    public C4432qd1(TabSwitcherModeTTPhone tabSwitcherModeTTPhone, boolean z) {
        this.H = tabSwitcherModeTTPhone;
        this.G = z;
    }

    @Override // defpackage.AbstractC2406em
    public void b(Animator animator) {
        this.H.setAlpha(1.0f);
        if (!this.G) {
            this.H.setVisibility(8);
        }
        IncognitoToggleTabLayout incognitoToggleTabLayout = this.H.K;
        if (incognitoToggleTabLayout != null) {
            incognitoToggleTabLayout.setClickable(true);
        }
        this.H.T = null;
    }
}
