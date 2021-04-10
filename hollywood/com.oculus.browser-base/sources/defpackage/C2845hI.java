package defpackage;

import org.chromium.chrome.browser.download.DownloadItem;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* renamed from: hI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2845hI extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final DownloadManagerService f10062a;
    public final DownloadItem b;

    public C2845hI(DownloadManagerService downloadManagerService, DownloadItem downloadItem) {
        this.f10062a = downloadManagerService;
        this.b = downloadItem;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f10062a.z(this.b, (C1640aI) obj);
    }
}
