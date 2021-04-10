package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: h71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2814h71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C2814h71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.setVisibility(8);
        TabGridDialogView tabGridDialogView = this.F;
        tabGridDialogView.V = null;
        tabGridDialogView.Q.clearFocus();
        TabGridDialogView tabGridDialogView2 = this.F;
        ViewGroup viewGroup = (ViewGroup) tabGridDialogView2.getParent();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != tabGridDialogView2) {
                Integer num = (Integer) tabGridDialogView2.i0.get(childAt);
                childAt.setImportantForAccessibility(num == null ? 0 : num.intValue());
            }
        }
        tabGridDialogView2.i0.clear();
    }
}
