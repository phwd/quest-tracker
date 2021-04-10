package org.chromium.chrome.browser;

import J.N;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NearOomMonitor implements ComponentCallbacks2 {
    public final long F;

    public NearOomMonitor(long j) {
        this.F = j;
        ContextUtils.getApplicationContext().registerComponentCallbacks(this);
    }

    public static NearOomMonitor create(long j) {
        return new NearOomMonitor(j);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
        N.MvDhdpTR(this.F, this);
    }

    public void onTrimMemory(int i) {
    }
}
