package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: El1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class El1 extends AnimatorListenerAdapter {
    public final /* synthetic */ ToolbarTablet F;

    public El1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    public void onAnimationEnd(Animator animator) {
        if (this.F.j0[0].getAlpha() == 0.0f) {
            ImageButton[] imageButtonArr = this.F.j0;
            for (ImageButton imageButton : imageButtonArr) {
                imageButton.setVisibility(8);
                imageButton.setAlpha(1.0f);
            }
            this.F.Z(false);
        }
        this.F.s0 = null;
    }
}
