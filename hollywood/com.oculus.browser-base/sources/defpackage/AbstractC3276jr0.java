package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.offlinepages.downloads.OfflinePageDownloadBridge;

/* renamed from: jr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3276jr0 {

    /* renamed from: a  reason: collision with root package name */
    public static C3105ir0 f10242a;

    public static C3105ir0 a() {
        Object obj = ThreadUtils.f10596a;
        if (f10242a == null) {
            AbstractC3960nr0 a2 = AbstractC2935hr0.a();
            D51 d51 = DownloadManagerService.p().f10662J;
            if (OfflinePageDownloadBridge.f10722a == null) {
                OfflinePageDownloadBridge.f10722a = new OfflinePageDownloadBridge();
            }
            f10242a = new C3105ir0(a2, d51);
        }
        return f10242a;
    }
}
