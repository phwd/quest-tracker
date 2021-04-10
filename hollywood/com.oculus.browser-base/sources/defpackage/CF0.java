package defpackage;

import J.N;
import java.util.HashSet;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* renamed from: CF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class CF0 {

    /* renamed from: a  reason: collision with root package name */
    public static final PU0 f7797a = NU0.f8549a;

    public static void a() {
        f7797a.m("Chrome.PriceTracking.PriceWelcome", false);
    }

    public static boolean b() {
        if (!c() || !f7797a.d("Chrome.PriceTracking.PriceDropAlerts", false)) {
            return false;
        }
        return true;
    }

    public static boolean c() {
        if (AbstractC4772sd1.e() && C5949zZ.a().c(Profile.b()).c() && N.Mfmn09fr(Profile.b())) {
            ProfileSyncService b = ProfileSyncService.b();
            if (b != null && b.m() && ((HashSet) b.c()).contains(14)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        return !f7797a.d("Chrome.PriceTracking.PriceWelcome", AbstractC4772sd1.e());
    }

    public static boolean e() {
        return c() && f7797a.d("Chrome.PriceTracking.TrackPricesOnTabs", AbstractC4772sd1.e());
    }
}
