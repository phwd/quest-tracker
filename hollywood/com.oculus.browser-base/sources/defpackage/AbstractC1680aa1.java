package defpackage;

import J.N;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tasks.tab_management.TabManagementDelegateImpl;

/* renamed from: aa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1680aa1 {
    public static TabManagementDelegateImpl a() {
        C4455ql0 ql0 = Z91.f9323a;
        if (!ql0.f()) {
            C0270Ei1 ei1 = new C0270Ei1();
            try {
                ql0.c().b("tab_management");
                ei1.close();
                if (!UmaSessionStats.b()) {
                    return null;
                }
                N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "DownloadAttempted");
                N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "DownloadAttempted");
                return null;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            if (UmaSessionStats.b()) {
                if (!N.M09VlOh_("TabGridLayoutAndroid")) {
                    N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "Downloaded_Control");
                }
                if (!N.M09VlOh_("TabGroupsAndroid")) {
                    N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "Downloaded_Control");
                }
            }
            return (TabManagementDelegateImpl) ql0.b();
        }
        throw th;
    }
}
