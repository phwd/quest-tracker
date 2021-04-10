package defpackage;

import J.N;
import android.os.Build;
import android.util.Pair;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerBridge;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.download.MimeUtils;

/* renamed from: lI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3528lI extends AbstractC2032cb {
    public final /* synthetic */ DownloadItem i;
    public final /* synthetic */ DownloadInfo j;
    public final /* synthetic */ boolean k;
    public final /* synthetic */ DownloadManagerService l;

    public C3528lI(DownloadManagerService downloadManagerService, DownloadItem downloadItem, DownloadInfo downloadInfo, boolean z) {
        this.l = downloadManagerService;
        this.i = downloadItem;
        this.j = downloadInfo;
        this.k = z;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        DownloadManagerService downloadManagerService = this.l;
        Set set = DownloadManagerService.F;
        Objects.requireNonNull(downloadManagerService);
        boolean e = ContentUriUtils.e(this.i.c.g);
        boolean z = false;
        boolean z2 = !N.M09VlOh_("UseDownloadOfflineContentProvider") && Build.VERSION.SDK_INT < 29;
        if (!e && z2) {
            DownloadInfo downloadInfo = this.j;
            long a2 = DownloadManagerBridge.a(downloadInfo.e, downloadInfo.f, downloadInfo.c, downloadInfo.g, downloadInfo.j, downloadInfo.i, downloadInfo.h, downloadInfo.l);
            e = a2 != -1;
            if (e) {
                this.i.c(a2);
            }
        }
        if (e && (MimeUtils.isOMADownloadDescription(this.i.c.c) || DownloadManagerService.m(this.i, this.k))) {
            z = true;
        }
        return Pair.create(Boolean.valueOf(e), Boolean.valueOf(z));
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Pair pair = (Pair) obj;
        DownloadInfo downloadInfo = this.i.c;
        if (((Boolean) pair.first).booleanValue()) {
            this.l.f10662J.i(downloadInfo, this.i.d, ((Boolean) pair.second).booleanValue(), this.k);
            Iterator it = this.l.N.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC4212pI) uq0.next()).a(downloadInfo);
                } else {
                    return;
                }
            }
        } else {
            DH b = DH.b(downloadInfo);
            b.F = 1;
            this.l.f10662J.e(b.a());
        }
    }
}
