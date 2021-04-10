package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import org.chromium.chrome.browser.metrics.UmaSessionStats;

/* renamed from: kp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacksC3442kp1 implements ComponentCallbacks {
    public final /* synthetic */ UmaSessionStats F;

    public ComponentCallbacksC3442kp1(UmaSessionStats umaSessionStats) {
        this.F = umaSessionStats;
    }

    public void onConfigurationChanged(Configuration configuration) {
        UmaSessionStats umaSessionStats = this.F;
        boolean z = true;
        if (configuration.keyboard == 1) {
            z = false;
        }
        umaSessionStats.f = z;
    }

    public void onLowMemory() {
    }
}
