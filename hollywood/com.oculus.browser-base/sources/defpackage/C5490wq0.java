package defpackage;

import J.N;
import java.util.Set;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: wq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5490wq0 extends AbstractC2032cb {
    public final /* synthetic */ DownloadItem i;
    public final /* synthetic */ C1820bI j;
    public final /* synthetic */ long k;
    public final /* synthetic */ String l;
    public final /* synthetic */ C0164Cq0 m;

    public C5490wq0(C0164Cq0 cq0, DownloadItem downloadItem, C1820bI bIVar, long j2, String str) {
        this.m = cq0;
        this.i = downloadItem;
        this.j = bIVar;
        this.k = j2;
        this.l = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        DownloadItem downloadItem = this.i;
        String str = this.j.c;
        Set set = DownloadManagerService.F;
        return Boolean.valueOf(DownloadManagerService.m(downloadItem, N.M4t0L845(str)));
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        DH dh;
        Boolean bool = (Boolean) obj;
        C1820bI bIVar = this.j;
        int i2 = bIVar.f9529a;
        if (i2 == 1) {
            DownloadInfo downloadInfo = this.i.c;
            if (downloadInfo == null) {
                dh = new DH();
            } else {
                dh = DH.b(downloadInfo);
            }
            dh.g = this.j.h;
            this.i.c = dh.a();
            C0164Cq0 cq0 = this.m;
            DownloadInfo downloadInfo2 = this.i.c;
            long j2 = this.k;
            String str = this.l;
            C5830yq0 yq0 = (C5830yq0) cq0.e.get(j2);
            if (yq0 == null) {
                yq0 = new C5830yq0();
                yq0.a("installNotifyURI", str);
            }
            if (!cq0.i(yq0, downloadInfo2, j2, "900 Success \n\r")) {
                cq0.k(yq0);
            }
            cq0.e.remove(j2);
            C0164Cq0.a(this.m, this.k);
            C0164Cq0.b(this.m, this.i, this.j.f9529a);
        } else if (i2 == 2) {
            this.m.f(this.i.c, this.k, bIVar.g, this.l);
            C0164Cq0.a(this.m, this.k);
            C0164Cq0.b(this.m, this.i, this.j.f9529a);
        }
    }
}
