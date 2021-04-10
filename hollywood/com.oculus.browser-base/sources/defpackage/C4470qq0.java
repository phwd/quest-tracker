package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: qq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4470qq0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final DownloadItem f11166a;

    public C4470qq0(DownloadItem downloadItem) {
        this.f11166a = downloadItem;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        DH dh;
        DownloadItem downloadItem = this.f11166a;
        C1820bI bIVar = (C1820bI) obj;
        DownloadManagerService p = DownloadManagerService.p();
        Objects.requireNonNull(p);
        DownloadInfo downloadInfo = downloadItem.c;
        if (downloadInfo == null) {
            dh = new DH();
        } else {
            dh = DH.b(downloadInfo);
        }
        dh.k = bIVar.f;
        dh.j = bIVar.e;
        if (!TextUtils.isEmpty(bIVar.b)) {
            dh.e = bIVar.b;
        }
        if (!TextUtils.isEmpty(bIVar.c)) {
            dh.c = bIVar.c;
        }
        dh.g = bIVar.h;
        downloadItem.c = dh.a();
        int i = bIVar.f9529a;
        if (i != 0) {
            if (i == 1) {
                C3870nI nIVar = new C3870nI(p, downloadItem, bIVar);
                Executor executor = AbstractC2032cb.f9616a;
                nIVar.f();
                ((ExecutorC1463Ya) executor).execute(nIVar.e);
            } else if (i == 2) {
                p.A(downloadItem, bIVar.g);
            }
        }
    }
}
