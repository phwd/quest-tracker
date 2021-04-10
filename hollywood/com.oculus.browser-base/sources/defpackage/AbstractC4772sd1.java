package defpackage;

import android.os.Build;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: sd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4772sd1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1426Xi f11289a = new C1426Xi("TabToGTSAnimation", "skip-slow-zooming", true);
    public static final Q21 b = new Q21("TabGridLayoutAndroid", "tab_grid_layout_android_new_tab_tile", "");
    public static final JG c = new JG("TabGridLayoutAndroid", "thumbnail_aspect_ratio", 1.0d);
    public static final C1426Xi d = new C1426Xi("TabGridLayoutAndroid", "enable_search_term_chip", false);
    public static final C1426Xi e = new C1426Xi("TabGridLayoutAndroid", "enable_price_tracking", false);
    public static final C1426Xi f = new C1426Xi("TabGridLayoutAndroid", "enable_search_term_chip_adaptive_icon", false);
    public static final C1426Xi g = new C1426Xi("TabGridLayoutAndroid", "enable_launch_bug_fix", false);
    public static final C1426Xi h = new C1426Xi("TabGridLayoutAndroid", "enable_launch_polish", false);
    public static final P20 i = new P20("TabToGTSAnimation", "zooming-min-sdk-version", 23);
    public static final P20 j = new P20("TabToGTSAnimation", "zooming-min-memory-mb", 2048);

    public static boolean a() {
        return CachedFeatureFlags.isEnabled("ConditionalTabStripAndroid") && !b() && h() && !AbstractC3293jx.b();
    }

    public static boolean b() {
        if (AbstractC2793h01.d() || DeviceFormFactor.a(ContextUtils.getApplicationContext())) {
            return false;
        }
        if ((C5052uE.a() || !CachedFeatureFlags.isEnabled("TabGridLayoutAndroid") || !h()) && !g() && !AbstractC2793h01.b()) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        return g.c();
    }

    public static boolean d() {
        return h.c();
    }

    public static boolean e() {
        return e.c() && !AbstractC2793h01.b();
    }

    public static boolean f() {
        return g() && CachedFeatureFlags.isEnabled("TabGroupsContinuationAndroid");
    }

    public static boolean g() {
        if (!AbstractC2793h01.d() && !DeviceFormFactor.a(ContextUtils.getApplicationContext()) && !C5052uE.a() && CachedFeatureFlags.isEnabled("TabGroupsAndroid") && h()) {
            return true;
        }
        return false;
    }

    public static boolean h() {
        return AbstractC1680aa1.a() != null;
    }

    public static boolean i() {
        return Double.compare(1.0d, c.c()) != 0;
    }

    public static boolean j() {
        P20 p20 = i;
        p20.c();
        P20 p202 = j;
        p202.c();
        return CachedFeatureFlags.isEnabled("TabToGTSAnimation") && Build.VERSION.SDK_INT >= p20.c() && SysUtils.a() / 1024 >= p202.c() && !AbstractC2793h01.c();
    }

    public static boolean k(boolean z) {
        return !C5052uE.a() && CachedFeatureFlags.isEnabled("InstantStart") && !z && !SysUtils.isLowEndDevice();
    }
}
