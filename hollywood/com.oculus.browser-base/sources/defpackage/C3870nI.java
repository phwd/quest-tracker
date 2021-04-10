package defpackage;

import J.N;
import java.util.Set;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.MimeUtils;

/* renamed from: nI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3870nI extends AbstractC2032cb {
    public final /* synthetic */ DownloadItem i;
    public final /* synthetic */ C1820bI j;
    public final /* synthetic */ DownloadManagerService k;

    public C3870nI(DownloadManagerService downloadManagerService, DownloadItem downloadItem, C1820bI bIVar) {
        this.k = downloadManagerService;
        this.i = downloadItem;
        this.j = bIVar;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        DownloadItem downloadItem = this.i;
        String str = downloadItem.c.c;
        Set set = DownloadManagerService.F;
        return Boolean.valueOf(DownloadManagerService.m(downloadItem, N.M4t0L845(str)));
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Boolean bool = (Boolean) obj;
        if (!MimeUtils.canAutoOpenMimeType(this.j.c) || !this.i.c.m || !bool.booleanValue()) {
            this.k.r(this.i.c.t).a(DownloadItem.a(this.i), null);
            return;
        }
        DownloadManagerService downloadManagerService = this.k;
        DownloadItem downloadItem = this.i;
        Set set = DownloadManagerService.F;
        downloadManagerService.u(downloadItem);
    }
}
