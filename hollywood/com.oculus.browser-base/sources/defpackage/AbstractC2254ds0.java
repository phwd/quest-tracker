package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: ds0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2254ds0 {

    /* renamed from: a  reason: collision with root package name */
    public static C1446Xr0 f9814a;
    public static Map b = new HashMap();

    public static void a(int i, String str) {
        AbstractC3364kK0.g("OfflinePages.TabRestore", i, 10);
    }

    public static C1446Xr0 b() {
        if (f9814a == null) {
            f9814a = new C1446Xr0(null);
        }
        return f9814a;
    }

    public static void c(long j, int i, Callback callback, Profile profile) {
        Objects.requireNonNull(b());
        OfflinePageBridge a2 = OfflinePageBridge.a(profile);
        if (a2 == null) {
            callback.onResult(null);
            return;
        }
        N.MBaVkYrR(a2.f10718a, a2, j, i, new C1141Sr0(callback));
    }

    public static OfflinePageItem d(WebContents webContents) {
        if (webContents == null) {
            return null;
        }
        C1446Xr0 b2 = b();
        Profile a2 = Profile.a(webContents);
        Objects.requireNonNull(b2);
        OfflinePageBridge a3 = OfflinePageBridge.a(a2);
        if (a3 == null) {
            return null;
        }
        return (OfflinePageItem) N.MzjNdQag(a3.f10718a, a3, webContents);
    }

    public static boolean e() {
        Objects.requireNonNull(b());
        return NetworkChangeNotifier.c();
    }

    public static boolean f(Tab tab) {
        WebContents l;
        Objects.requireNonNull(b());
        if (tab == null || (l = tab.l()) == null) {
            return false;
        }
        C1446Xr0 b2 = b();
        Profile a2 = Profile.a(tab.l());
        Objects.requireNonNull(b2);
        OfflinePageBridge a3 = OfflinePageBridge.a(a2);
        if (a3 == null) {
            return false;
        }
        return N.Mmgl0zEx(a3.f10718a, a3, l);
    }

    public static boolean g(Uri uri) {
        return TextUtils.equals(uri.getScheme(), "content") || TextUtils.equals(uri.getScheme(), "file");
    }

    public static boolean h(WebContents webContents) {
        OfflinePageBridge a2;
        Objects.requireNonNull(b());
        if (webContents == null || (a2 = OfflinePageBridge.a(Profile.a(webContents))) == null) {
            return false;
        }
        return N.MD0P9_ar(a2.f10718a, a2, webContents);
    }

    public static void i(WebContents webContents, AbstractC1385Wr0 wr0) {
        String str;
        OfflinePageItem d = d(webContents);
        if (h(webContents) || d == null) {
            LoadUrlParams loadUrlParams = new LoadUrlParams(HG.b(webContents.u()), 33554440);
            C1446Xr0 b2 = b();
            Profile a2 = Profile.a(webContents);
            Objects.requireNonNull(b2);
            OfflinePageBridge a3 = OfflinePageBridge.a(a2);
            if (a3 == null) {
                str = "";
            } else {
                str = N.MRMfaXXV(a3.f10718a, a3, webContents);
            }
            loadUrlParams.f = str;
            wr0.c(loadUrlParams);
            return;
        }
        wr0.c(new LoadUrlParams(d.f10719a, 33554440));
    }

    public static void j(WindowAndroid windowAndroid, String str, String str2, String str3, File file, Callback callback) {
        AbstractC3535lK0.a("OfflinePages.Sharing.SharePageFromOverflowMenu");
        C1263Ur0 ur0 = new C1263Ur0(str3, str, file, windowAndroid, str2, callback);
        Executor executor = AbstractC2032cb.f9616a;
        ur0.f();
        ((ExecutorC1463Ya) executor).execute(ur0.e);
    }

    public static void k(OfflinePageItem offlinePageItem, WindowAndroid windowAndroid, Callback callback) {
        if (offlinePageItem != null) {
            j(windowAndroid, offlinePageItem.f10719a, offlinePageItem.d, offlinePageItem.e, new File(offlinePageItem.e), callback);
        }
    }
}
