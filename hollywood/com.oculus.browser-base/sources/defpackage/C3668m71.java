package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;

/* renamed from: m71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3668m71 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabGridDialogView F;

    public C3668m71(TabGridDialogView tabGridDialogView) {
        this.F = tabGridDialogView;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.Q.setTranslationX(0.0f);
        this.F.Q.setTranslationY(0.0f);
        this.F.Q.setScaleX(1.0f);
        this.F.Q.setScaleY(1.0f);
    }
}
