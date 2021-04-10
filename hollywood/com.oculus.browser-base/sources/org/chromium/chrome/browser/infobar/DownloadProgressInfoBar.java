package org.chromium.chrome.browser.infobar;

import org.chromium.chrome.browser.download.DownloadInfoBarController$DownloadProgressInfoBarData;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadProgressInfoBar extends InfoBar {
    public final Client I;

    /* renamed from: J  reason: collision with root package name */
    public DownloadInfoBarController$DownloadProgressInfoBarData f10683J;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface Client {
        void a(boolean z);
    }

    public DownloadProgressInfoBar(Client client, DownloadInfoBarController$DownloadProgressInfoBarData downloadInfoBarController$DownloadProgressInfoBarData) {
        super(downloadInfoBarController$DownloadProgressInfoBarData.e, 0, null, null);
        this.f10683J = downloadInfoBarController$DownloadProgressInfoBarData;
        this.I = client;
    }

    public static DownloadProgressInfoBar create(Client client, DownloadInfoBarController$DownloadProgressInfoBarData downloadInfoBarController$DownloadProgressInfoBarData) {
        return new DownloadProgressInfoBar(client, downloadInfoBarController$DownloadProgressInfoBarData);
    }

    @Override // defpackage.C10, org.chromium.components.infobars.InfoBar
    public CharSequence b() {
        return null;
    }
}
