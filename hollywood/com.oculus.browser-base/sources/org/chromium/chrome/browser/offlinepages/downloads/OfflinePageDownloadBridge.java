package org.chromium.chrome.browser.offlinepages.downloads;

import J.N;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflinePageDownloadBridge {

    /* renamed from: a  reason: collision with root package name */
    public static OfflinePageDownloadBridge f10722a;

    public OfflinePageDownloadBridge() {
        N.MnuITPuJ(this);
    }

    public static boolean maybeSuppressNotification(String str, String str2) {
        C0788My a2;
        BI b;
        C0593Jr0 jr0 = new C0593Jr0(str);
        Objects.requireNonNull(AppHooks.get());
        if (!Collections.emptyList().contains(jr0.f8316a)) {
            return false;
        }
        D51 d51 = DownloadManagerService.p().f10662J;
        if (!(d51 == null || (b = DI.f7880a.b((a2 = U70.a(true, str2)))) == null)) {
            DH dh = new DH();
            dh.z = a2;
            d51.j(b.b, dh.a());
        }
        return true;
    }

    public static void openItem(String str, long j, int i, boolean z, boolean z2) {
        AbstractC2254ds0.c(j, i, new C0532Ir0(i, j, z2, z), Profile.b());
    }

    public static void showDownloadingToast() {
        if (N.M09VlOh_("DownloadProgressInfoBar")) {
            DownloadManagerService.p().r(false).f(null, true, false, false);
        } else {
            C1184Ti1.a(ContextUtils.getApplicationContext(), R.string.f51710_resource_name_obfuscated_RES_2131952488, 0).b.show();
        }
    }
}
