package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadItem;

/* renamed from: FH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class FH extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final KH f8007a;
    public final DownloadItem b;

    public FH(KH kh, DownloadItem downloadItem) {
        this.f8007a = kh;
        this.b = downloadItem;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        KH kh = this.f8007a;
        DownloadItem downloadItem = this.b;
        Objects.requireNonNull(kh);
        if (((Boolean) obj).booleanValue()) {
            kh.d(downloadItem.f10660a);
        } else {
            kh.f(DownloadItem.a(downloadItem), false, false, false);
        }
    }
}
