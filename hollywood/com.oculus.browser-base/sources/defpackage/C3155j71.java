package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: j71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3155j71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C3155j71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.M.setVisibility(4);
        this.F.W = null;
    }

    public void onAnimationStart(Animator animator) {
        Animator animator2 = this.F.W;
        if (animator2 != null) {
            animator2.end();
        }
        TabGridDialogView tabGridDialogView = this.F;
        tabGridDialogView.W = tabGridDialogView.d0;
    }
}
