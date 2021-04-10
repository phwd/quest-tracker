package defpackage;

import org.chromium.chrome.browser.download.DownloadInfoBarController$DownloadProgressInfoBarData;

/* renamed from: GH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class GH implements Runnable {
    public final KH F;
    public final int G;
    public final int H;

    public GH(KH kh, int i, int i2) {
        this.F = kh;
        this.G = i;
        this.H = i2;
    }

    public void run() {
        KH kh = this.F;
        int i = this.G;
        int i2 = this.H;
        kh.O = null;
        DownloadInfoBarController$DownloadProgressInfoBarData downloadInfoBarController$DownloadProgressInfoBarData = kh.Q;
        if (downloadInfoBarController$DownloadProgressInfoBarData != null) {
            downloadInfoBarController$DownloadProgressInfoBarData.k = -1;
        }
        if (i == 2) {
            kh.b(Integer.valueOf(i2));
        }
        kh.f(null, false, false, false);
    }
}
