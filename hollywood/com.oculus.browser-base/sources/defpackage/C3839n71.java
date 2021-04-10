package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: n71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3839n71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C3839n71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.f10781J.setAlpha(0.0f);
        this.F.K.setAlpha(0.0f);
    }

    public void onAnimationStart(Animator animator) {
        this.F.f10781J.bringToFront();
        this.F.K.bringToFront();
    }
}
