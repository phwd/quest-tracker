package defpackage;

import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: gI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2674gI implements Runnable {
    public final DownloadManagerService F;

    public RunnableC2674gI(DownloadManagerService downloadManagerService) {
        this.F = downloadManagerService;
    }

    public void run() {
        DownloadManagerService downloadManagerService = this.F;
        downloadManagerService.U = false;
        downloadManagerService.I();
    }
}
