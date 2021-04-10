package org.chromium.chrome.browser.tasks.tab_management;

import J.N;
import android.view.ViewGroup;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.metrics.UmaSessionStats;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabManagementDelegateImpl {
    public AbstractC5959zc1 a(ChromeActivity chromeActivity, ViewGroup viewGroup, GP0 gp0) {
        if (UmaSessionStats.b()) {
            N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "Downloaded_Enabled");
        }
        return new C1349Wc1(chromeActivity, chromeActivity.Y, chromeActivity.P(), chromeActivity.x0, chromeActivity.M0(), chromeActivity, chromeActivity, viewGroup, chromeActivity.K0, chromeActivity.Z, gp0, (!AbstractC4772sd1.f() || !SysUtils.isLowEndDevice()) ? 0 : 3);
    }

    public AbstractC4096of1 b(ChromeActivity chromeActivity, GP0 gp0, UH0 uh0, int i, Q31 q31, boolean z, boolean z2) {
        return new C4778sf1(chromeActivity, gp0, uh0, i, q31, z, z2);
    }
}
