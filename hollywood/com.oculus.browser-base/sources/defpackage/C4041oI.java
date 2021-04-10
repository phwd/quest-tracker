package defpackage;

import J.N;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.MimeUtils;

/* renamed from: oI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4041oI extends AbstractC2032cb {
    public final /* synthetic */ DownloadItem i;
    public final /* synthetic */ Callback j;

    public C4041oI(DownloadManagerService downloadManagerService, DownloadItem downloadItem, Callback callback) {
        this.i = downloadItem;
        this.j = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        DownloadInfo downloadInfo = this.i.c;
        String str = downloadInfo.c;
        Set set = DownloadManagerService.F;
        boolean z = false;
        if ((MimeUtils.isOMADownloadDescription(downloadInfo.c) || DownloadManagerService.m(this.i, N.M4t0L845(str))) && MimeUtils.canAutoOpenMimeType(downloadInfo.c) && downloadInfo.m) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.onResult((Boolean) obj);
    }
}
