package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;

/* renamed from: ti0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4956ti0 extends AnimatorListenerAdapter {
    public final /* synthetic */ MenuButton F;

    public C4956ti0(MenuButton menuButton) {
        this.F = menuButton;
    }

    public void onAnimationCancel(Animator animator) {
        this.F.N = false;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.N = false;
    }

    public void onAnimationStart(Animator animator) {
        this.F.N = true;
    }
}
