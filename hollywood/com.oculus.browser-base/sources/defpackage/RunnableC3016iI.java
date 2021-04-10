package defpackage;

import J.N;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* renamed from: iI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3016iI implements Runnable {
    public final DownloadManagerService F;
    public final String G;
    public final OTRProfileID H;

    public RunnableC3016iI(DownloadManagerService downloadManagerService, String str, OTRProfileID oTRProfileID) {
        this.F = downloadManagerService;
        this.G = str;
        this.H = oTRProfileID;
    }

    public void run() {
        DownloadManagerService downloadManagerService = this.F;
        String str = this.G;
        N.M8Q_hBf$(downloadManagerService.t(), downloadManagerService, str, Z00.d(this.H));
        downloadManagerService.I.remove(str);
        downloadManagerService.G(str);
        DownloadManagerService.F.remove(str);
    }
}
