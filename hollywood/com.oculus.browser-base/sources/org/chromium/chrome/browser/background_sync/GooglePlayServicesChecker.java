package org.chromium.chrome.browser.background_sync;

import java.util.Objects;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GooglePlayServicesChecker {
    public static boolean shouldDisableBackgroundSync() {
        Objects.requireNonNull(YM.f9268a);
        ContextUtils.getApplicationContext();
        AbstractC1220Ua0.d("PlayServicesChecker", "Disabling Background Sync because Play Services is not up to date.", new Object[0]);
        AbstractC3100ip1.f10165a.a("BackgroundSync.LaunchTask.PlayServicesAvailable", false);
        return true;
    }
}
