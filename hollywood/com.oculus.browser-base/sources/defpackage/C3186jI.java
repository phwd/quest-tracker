package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.components.prefs.PrefService;

/* renamed from: jI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3186jI extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final DownloadManagerService f10198a;
    public final C4721sH b;
    public final List c;
    public final PrefService d;

    public C3186jI(DownloadManagerService downloadManagerService, C4721sH sHVar, List list, PrefService prefService) {
        this.f10198a = downloadManagerService;
        this.b = sHVar;
        this.c = list;
        this.d = prefService;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        boolean z;
        DownloadInfo downloadInfo;
        int i;
        DownloadManagerService downloadManagerService = this.f10198a;
        C4721sH sHVar = this.b;
        List<DownloadItem> list = this.c;
        PrefService prefService = this.d;
        ArrayList arrayList = (ArrayList) obj;
        Objects.requireNonNull(downloadManagerService);
        if (arrayList.size() <= 1) {
            String str = sHVar.b ? sHVar.f : null;
            for (DownloadItem downloadItem : list) {
                String str2 = downloadItem.c.g;
                if (!TextUtils.isEmpty(str2) && !str2.contains(str) && !ContentUriUtils.e(str2)) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        LF lf = (LF) it.next();
                        if (!TextUtils.isEmpty(lf.b) && str2.contains(lf.b)) {
                            break;
                        }
                    }
                    downloadInfo = downloadItem.c;
                    i = downloadInfo.w;
                    if (((i != 3 && !downloadInfo.r) || i == 2) && z) {
                        downloadManagerService.L.post(new RunnableC3357kI(downloadManagerService));
                        N.Mf2ABpoH(prefService.f10883a, "download.show_missing_sd_card_error_android", false);
                        return;
                    }
                }
                z = false;
                downloadInfo = downloadItem.c;
                i = downloadInfo.w;
                if ((i != 3 && !downloadInfo.r) || i == 2) {
                }
            }
        }
    }
}
