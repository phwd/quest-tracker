package defpackage;

import J.N;
import org.chromium.chrome.browser.download.DownloadController;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: AG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AG0 implements Runnable {
    public AG0(OG0 og0) {
    }

    public void run() {
        if (!AbstractC1575Zv.e().g("type")) {
            DownloadManagerService p = DownloadManagerService.p();
            if (!N.M09VlOh_("UseDownloadOfflineContentProvider")) {
                DownloadController.f10655a = p;
            }
        }
    }
}
