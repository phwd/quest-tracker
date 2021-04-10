package defpackage;

import android.os.Environment;
import android.util.Pair;
import java.io.File;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadController;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: zr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C6002zr extends AbstractC2032cb {
    public final /* synthetic */ DownloadInfo i;
    public final /* synthetic */ String j;
    public final /* synthetic */ String k;
    public final /* synthetic */ C0043Ar l;

    public C6002zr(C0043Ar ar, DownloadInfo downloadInfo, String str, String str2) {
        this.l = ar;
        this.i = downloadInfo;
        this.j = str;
        this.k = str2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        String externalStorageState = Environment.getExternalStorageState();
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!externalStoragePublicDirectory.mkdir() && !externalStoragePublicDirectory.isDirectory()) {
            externalStoragePublicDirectory = null;
        }
        return new Pair(externalStorageState, externalStoragePublicDirectory);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Pair pair = (Pair) obj;
        String str = (String) pair.first;
        File file = (File) pair.second;
        C0043Ar ar = this.l;
        DownloadInfo downloadInfo = this.i;
        Objects.requireNonNull(ar);
        int i2 = 1007;
        boolean z = false;
        if (file == null) {
            AbstractC1220Ua0.a("Download", "Download failed: no SD card", new Object[0]);
            DownloadManagerService.p().A(new DownloadItem(false, downloadInfo), 1007);
        } else if (!str.equals("mounted")) {
            if (str.equals("shared")) {
                AbstractC1220Ua0.a("Download", "Download failed: SD card unavailable", new Object[0]);
                i2 = 1001;
            } else {
                AbstractC1220Ua0.a("Download", "Download failed: no SD card", new Object[0]);
            }
            DownloadManagerService.p().A(new DownloadItem(false, downloadInfo), i2);
        } else {
            z = true;
        }
        if (z) {
            C0043Ar ar2 = this.l;
            DownloadInfo downloadInfo2 = this.i;
            Objects.requireNonNull(ar2);
            String str2 = downloadInfo2.f10658a;
            if (str2 != null) {
                DH b = DH.b(this.i);
                b.f7878a = str2;
                b.c = this.j;
                b.f = str2;
                b.e = this.k;
                b.l = true;
                DownloadController.a(b.a());
                DownloadController.closeTabIfBlank(this.l.F);
            }
        }
    }
}
