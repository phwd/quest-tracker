package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.firstrun.TosAndUmaFirstRunFragmentWithEnterpriseSupport;
import org.chromium.components.browser_ui.widget.LoadingView;

/* renamed from: N90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N90 implements Runnable {
    public final /* synthetic */ LoadingView F;

    public N90(LoadingView loadingView) {
        this.F = loadingView;
    }

    public void run() {
        LoadingView loadingView = this.F;
        if (loadingView.f10819J) {
            loadingView.G = SystemClock.elapsedRealtime();
            this.F.setVisibility(0);
            this.F.setAlpha(1.0f);
            for (Q90 q90 : this.F.H) {
                ((TosAndUmaFirstRunFragmentWithEnterpriseSupport) q90).H0.setVisibility(0);
            }
        }
    }
}
