package org.chromium.chrome.browser.offlinepages.prefetch;

import org.chromium.chrome.browser.download.DownloadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefetchNotificationServiceBridge {
    public static void launchDownloadHome() {
        DownloadUtils.showDownloadManager(null, null, 12, true);
    }
}
