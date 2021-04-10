package defpackage;

import java.util.concurrent.Executor;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.MimeUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Ar  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0043Ar extends Pr1 {
    public Tab F;

    public C0043Ar(Tab tab) {
        this.F = tab;
    }

    public void c(DownloadInfo downloadInfo) {
        String str = downloadInfo.e;
        C6002zr zrVar = new C6002zr(this, downloadInfo, MimeUtils.remapGenericMimeType(downloadInfo.c, downloadInfo.f10658a, str), str);
        Executor executor = AbstractC2032cb.f9616a;
        zrVar.f();
        ((ExecutorC1463Ya) executor).execute(zrVar.e);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        this.F = null;
    }
}
