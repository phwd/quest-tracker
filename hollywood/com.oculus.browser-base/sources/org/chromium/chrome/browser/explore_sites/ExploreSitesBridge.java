package org.chromium.chrome.browser.explore_sites;

import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExploreSitesBridge {
    public static float getScaleFactorFromDevice() {
        return YF.b(ContextUtils.getApplicationContext()).e;
    }

    public static void scheduleDailyTask() {
        ExploreSitesBackgroundTask.l(false);
    }
}
