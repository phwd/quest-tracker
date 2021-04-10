package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;

/* renamed from: HK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HK extends AnimatorListenerAdapter {
    public final /* synthetic */ EmptyBackgroundViewTablet F;

    public HK(EmptyBackgroundViewTablet emptyBackgroundViewTablet) {
        this.F = emptyBackgroundViewTablet;
    }

    public void onAnimationEnd(Animator animator) {
        EmptyBackgroundViewTablet emptyBackgroundViewTablet = this.F;
        emptyBackgroundViewTablet.I = null;
        emptyBackgroundViewTablet.getRootView().findViewById(R.id.control_container).setVisibility(4);
    }

    public void onAnimationStart(Animator animator) {
        this.F.setVisibility(0);
    }
}
