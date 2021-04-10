package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: e71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2301e71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C2301e71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationStart(Animator animator) {
        this.F.f10781J.setAlpha(1.0f);
    }
}
