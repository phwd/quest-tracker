package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: f71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2472f71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C2472f71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        View view = this.F.L;
        if (view != null) {
            view.setAlpha(1.0f);
        }
    }
}
