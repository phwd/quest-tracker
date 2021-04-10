package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.ImageButton;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: Dl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dl1 extends AnimatorListenerAdapter {
    public final /* synthetic */ ToolbarTablet F;

    public Dl1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.s0 = null;
    }

    public void onAnimationStart(Animator animator) {
        for (ImageButton imageButton : this.F.j0) {
            imageButton.setVisibility(0);
        }
        this.F.Z(true);
    }
}
