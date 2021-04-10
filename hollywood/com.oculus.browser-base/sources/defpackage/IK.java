package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;

/* renamed from: IK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IK extends AnimatorListenerAdapter {
    public final /* synthetic */ EmptyBackgroundViewTablet F;

    public IK(EmptyBackgroundViewTablet emptyBackgroundViewTablet) {
        this.F = emptyBackgroundViewTablet;
    }

    public void onAnimationEnd(Animator animator) {
        this.F.setVisibility(8);
        EmptyBackgroundViewTablet emptyBackgroundViewTablet = this.F;
        emptyBackgroundViewTablet.I = null;
        emptyBackgroundViewTablet.L.setEnabled(true);
    }

    public void onAnimationStart(Animator animator) {
        this.F.setVisibility(0);
        this.F.getRootView().findViewById(R.id.control_container).setVisibility(0);
        this.F.L.setEnabled(false);
    }
}
