package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: l71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3497l71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C3497l71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationStart(Animator animator) {
        this.F.Q.bringToFront();
    }
}
