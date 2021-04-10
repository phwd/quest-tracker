package defpackage;

/* renamed from: jx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3293jx {

    /* renamed from: a  reason: collision with root package name */
    public static final P20 f10251a = new P20("ConditionalTabStripAndroid", "conditional_tab_strip_session_time_ms", 3600000);
    public static final P20 b = new P20("ConditionalTabStripAndroid", "conditional_tab_strip_infobar_limit", 6);
    public static final P20 c = new P20("ConditionalTabStripAndroid", "conditional_tab_strip_infobar_period", 3);

    public static int a() {
        return NU0.f8549a.f("Chrome.ConditionalTabStrip.FeatureStatus", 2);
    }

    public static boolean b() {
        return NU0.f8549a.d("Chrome.ConditionalTabStrip.OptOut", false);
    }

    public static void c(int i) {
        AbstractC3364kK0.g("TabStrip.UserStatus", i, 5);
    }

    public static void d(int i) {
        NU0.f8549a.n("Chrome.ConditionalTabStrip.ContinuousDismissCounter", i);
    }

    public static void e(int i) {
        NU0.f8549a.n("Chrome.ConditionalTabStrip.FeatureStatus", i);
    }
}
