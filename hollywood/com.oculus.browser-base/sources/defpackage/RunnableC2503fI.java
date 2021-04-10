package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: fI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2503fI implements Runnable {
    public final DownloadManagerService F;
    public final DownloadItem G;

    public RunnableC2503fI(DownloadManagerService downloadManagerService, DownloadItem downloadItem) {
        this.F = downloadManagerService;
        this.G = downloadItem;
    }

    public void run() {
        DownloadManagerService downloadManagerService = this.F;
        DownloadItem downloadItem = this.G;
        Objects.requireNonNull(downloadManagerService);
        downloadManagerService.j(U70.a(false, downloadItem.b()), downloadItem, false);
    }
}
