package defpackage;

import java.util.Objects;
import org.chromium.components.browser_ui.widget.LoadingView;

/* renamed from: P90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P90 implements Runnable {
    public final /* synthetic */ LoadingView F;

    public P90(LoadingView loadingView) {
        this.F = loadingView;
    }

    public void run() {
        LoadingView loadingView = this.F;
        int i = LoadingView.F;
        Objects.requireNonNull(loadingView);
        this.F.animate().alpha(0.0f).setInterpolator(animation.InterpolatorC5286vf.e).setListener(new O90(this));
    }
}
