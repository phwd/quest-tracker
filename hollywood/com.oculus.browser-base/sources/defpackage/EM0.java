package defpackage;

import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: EM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class EM0 {

    /* renamed from: a  reason: collision with root package name */
    public static final P20 f7957a = new P20("TabSwitcherOnReturn", "tab_switcher_on_return_time_ms", -1);
    public static boolean b;

    public static String a(boolean z) {
        return z ? ".Cold" : ".Warm";
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals("chrome://newtab/") || str.equals("chrome-native://newtab/") || str.equals("about:newtab")) {
            return true;
        }
        return false;
    }

    public static String c(int i) {
        return AbstractC2531fV.h(AbstractC2531fV.i("."), i == 0 ? "0" : i <= 2 ? "1~2" : i <= 5 ? "3~5" : i <= 10 ? "6~10" : i <= 20 ? "11~20" : "20+", "thumbnails");
    }

    public static boolean d() {
        return e() && !AbstractC2793h01.g.c();
    }

    public static boolean e() {
        String b2 = QX.b();
        return AbstractC2793h01.c() && (TextUtils.isEmpty(b2) || b(b2)) && !C0283Ep.h().d() && !DeviceFormFactor.a(ContextUtils.getApplicationContext());
    }

    public static boolean f(boolean z) {
        return !z && d();
    }

    public static boolean g(String str, int i, Boolean bool, Tab tab) {
        return h(new LoadUrlParams(str, i), null, null, bool, tab);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (defpackage.AbstractC5154ur1.g(r0) == false) goto L_0x001e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean h(org.chromium.content_public.browser.LoadUrlParams r3, java.lang.String r4, byte[] r5, java.lang.Boolean r6, org.chromium.chrome.browser.tab.Tab r7) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.EM0.h(org.chromium.content_public.browser.LoadUrlParams, java.lang.String, byte[], java.lang.Boolean, org.chromium.chrome.browser.tab.Tab):boolean");
    }
}
