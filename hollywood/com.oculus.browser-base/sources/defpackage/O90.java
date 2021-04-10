package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.components.browser_ui.widget.LoadingView;

/* renamed from: O90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O90 extends AnimatorListenerAdapter {
    public final /* synthetic */ P90 F;

    public O90(P90 p90) {
        this.F = p90;
    }

    public void onAnimationEnd(Animator animator) {
        LoadingView loadingView = this.F.F;
        int i = LoadingView.F;
        loadingView.b();
    }
}
