package defpackage;

import android.text.TextUtils;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;

/* renamed from: vq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5320vq0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0164Cq0 f11502a;
    public final long b;
    public final String c;

    public C5320vq0(C0164Cq0 cq0, long j, String str) {
        this.f11502a = cq0;
        this.b = j;
        this.c = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        DH dh;
        C0164Cq0 cq0 = this.f11502a;
        long j = this.b;
        String str = this.c;
        C1820bI bIVar = (C1820bI) obj;
        DownloadItem downloadItem = (DownloadItem) cq0.d.get(j);
        if (downloadItem == null) {
            downloadItem = new DownloadItem(true, null);
            downloadItem.d = j;
            downloadItem.f10660a.b = downloadItem.b();
        }
        DownloadInfo downloadInfo = downloadItem.c;
        if (downloadInfo == null) {
            dh = new DH();
        } else {
            dh = DH.b(downloadInfo);
        }
        dh.j = bIVar.e;
        dh.k = bIVar.f;
        if (!TextUtils.isEmpty(bIVar.b)) {
            dh.e = bIVar.b;
        }
        if (!TextUtils.isEmpty(bIVar.c)) {
            dh.c = bIVar.c;
        }
        dh.g = bIVar.h;
        downloadItem.c = dh.a();
        C5490wq0 wq0 = new C5490wq0(cq0, downloadItem, bIVar, j, str);
        Executor executor = AbstractC2032cb.f9616a;
        wq0.f();
        ((ExecutorC1463Ya) executor).execute(wq0.e);
        cq0.h(j);
    }
}
