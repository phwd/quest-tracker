package defpackage;

import android.content.IntentFilter;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: uq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5150uq0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0164Cq0 f11439a;
    public final DownloadItem b;

    public C5150uq0(C0164Cq0 cq0, DownloadItem downloadItem) {
        this.f11439a = cq0;
        this.b = downloadItem;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0164Cq0 cq0 = this.f11439a;
        DownloadItem downloadItem = this.b;
        C1640aI aIVar = (C1640aI) obj;
        Objects.requireNonNull(cq0);
        long j = downloadItem.d;
        downloadItem.c(aIVar.f9423a);
        boolean z = cq0.e.get(j) != null;
        if (aIVar.b) {
            if (cq0.d.size() == 0) {
                cq0.b.registerReceiver(cq0, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            }
            cq0.d.put(aIVar.f9423a, downloadItem);
            if (z) {
                cq0.e.remove(j);
                cq0.e.put(aIVar.f9423a, (C5830yq0) cq0.e.get(j));
                String str = (String) ((C5830yq0) cq0.e.get(aIVar.f9423a)).f11703a.get("installNotifyURI");
                if (!TextUtils.isEmpty(str)) {
                    long j2 = aIVar.f9423a;
                    String str2 = String.valueOf(j2) + "," + str;
                    Set e = C0164Cq0.e(cq0.c, "PendingOMADownloads");
                    ((HashSet) e).add(str2);
                    DownloadManagerService.J(cq0.c, "PendingOMADownloads", e, false);
                }
            }
            DownloadManagerService.p().z(downloadItem, aIVar);
            Iterator it = cq0.f.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0103Bq0) uq0.next()).a(aIVar.f9423a);
                } else {
                    return;
                }
            }
        } else if (z) {
            cq0.f(downloadItem.c, j, 1000, null);
        }
    }
}
