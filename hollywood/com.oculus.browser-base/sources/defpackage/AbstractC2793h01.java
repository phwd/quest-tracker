package defpackage;

import J.N;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: h01  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2793h01 {

    /* renamed from: a  reason: collision with root package name */
    public static final Q21 f10042a = new Q21("StartSurfaceAndroid", "start_surface_variation", "");
    public static final C1426Xi b = new C1426Xi("StartSurfaceAndroid", "exclude_mv_tiles", false);
    public static final C1426Xi c = new C1426Xi("StartSurfaceAndroid", "hide_switch_when_no_incognito_tabs", false);
    public static final C1426Xi d = new C1426Xi("StartSurfaceAndroid", "hide_incognito_switch", false);
    public static final C1426Xi e = new C1426Xi("StartSurfaceAndroid", "show_last_active_tab_only", false);
    public static final C1426Xi f = new C1426Xi("StartSurfaceAndroid", "show_stack_tab_switcher", false);
    public static final C1426Xi g = new C1426Xi("StartSurfaceAndroid", "open_ntp_instead_of_start", false);
    public static final Q21 h = new Q21("StartSurfaceAndroid", "omnibox_scroll_mode", "");
    public static final C1426Xi i = new C1426Xi("StartSurfaceAndroid", "trendy_enabled", false);
    public static final P20 j = new P20("StartSurfaceAndroid", "trendy_success_min_period_ms", 86400000);
    public static final P20 k = new P20("StartSurfaceAndroid", "trendy_failure_min_period_ms", 7200000);
    public static final Q21 l = new Q21("StartSurfaceAndroid", "trendy_endpoint", "https://trends.google.com/trends/trendingsearches/daily/rss?lite=true&safe=true&geo=");

    public static String a(String str, boolean z) {
        StringBuilder j2 = AbstractC2531fV.j("Startup.Android.", str);
        j2.append(z ? ".Instant" : ".NoInstant");
        return j2.toString();
    }

    public static boolean b() {
        return CachedFeatureFlags.isEnabled("StartSurfaceAndroid") && !SysUtils.isLowEndDevice();
    }

    public static boolean c() {
        return b() && f10042a.c().equals("single");
    }

    public static boolean d() {
        return c() && f.c();
    }

    public static void e(String str, long j2, boolean z) {
        if (j2 >= 0) {
            AbstractC1220Ua0.d("StartSurfaceConfig", "Recorded %s = %d ms", a(str, z), Long.valueOf(j2));
            AbstractC3364kK0.k(a(str, z), j2);
        }
    }

    public static void f() {
        NU0.f8549a.m("Chrome.Feed.ArticlesListVisible", N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "ntp_snippets.list_visible"));
    }
}
