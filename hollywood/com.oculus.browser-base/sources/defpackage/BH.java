package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.download.home.toolbar.DownloadHomeToolbar;

/* renamed from: BH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BH implements Runnable {
    public final DownloadHomeToolbar F;

    public BH(DownloadHomeToolbar downloadHomeToolbar) {
        this.F = downloadHomeToolbar;
    }

    public void run() {
        DownloadHomeToolbar downloadHomeToolbar = this.F;
        Objects.requireNonNull(downloadHomeToolbar);
        Yo1 yo1 = new Yo1(downloadHomeToolbar);
        downloadHomeToolbar.b1 = yo1;
        downloadHomeToolbar.M(yo1);
    }
}
