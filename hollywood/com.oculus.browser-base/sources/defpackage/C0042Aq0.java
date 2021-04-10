package defpackage;

import android.app.DownloadManager;
import org.chromium.chrome.browser.download.DownloadInfo;

/* renamed from: Aq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0042Aq0 extends AbstractC2032cb {
    public final C5830yq0 i;
    public final DownloadInfo j;
    public final String k;
    public final long l;
    public final /* synthetic */ C0164Cq0 m;

    public C0042Aq0(C0164Cq0 cq0, C5830yq0 yq0, DownloadInfo downloadInfo, long j2, String str) {
        this.m = cq0;
        this.i = yq0;
        this.j = downloadInfo;
        this.k = str;
        this.l = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ad, code lost:
        if (r6 != null) goto L_0x00af;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097 A[Catch:{ MalformedURLException -> 0x00a3, IOException -> 0x0098, IllegalStateException -> 0x008d, all -> 0x008a }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a2 A[Catch:{ MalformedURLException -> 0x00a3, IOException -> 0x0098, IllegalStateException -> 0x008d, all -> 0x008a }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0158  */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 348
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0042Aq0.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        if (((Boolean) obj).booleanValue()) {
            this.m.k(this.i);
        } else if (this.l != -1) {
            ((DownloadManager) this.m.b.getSystemService("download")).remove(this.l);
        }
    }
}
