package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: g71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2643g71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C2643g71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        TabGridDialogView tabGridDialogView = this.F;
        tabGridDialogView.V = null;
        tabGridDialogView.Q.requestFocus();
        this.F.Q.sendAccessibilityEvent(8);
        TabGridDialogView tabGridDialogView2 = this.F;
        ViewGroup viewGroup = (ViewGroup) tabGridDialogView2.getParent();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != tabGridDialogView2) {
                tabGridDialogView2.i0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                childAt.setImportantForAccessibility(4);
            }
        }
    }
}
