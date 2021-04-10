package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: CM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class CM {
    public static void a(AbstractC5127ui1 ui1, Profile profile, ChromeActivity chromeActivity) {
        if (!chromeActivity.v()) {
            String string = ui1.getContext().getString(R.string.f52040_resource_name_obfuscated_RES_2131952521);
            String string2 = ui1.getContext().getString(R.string.f52050_resource_name_obfuscated_RES_2131952522);
            Tm1 a2 = Um1.a(profile);
            if (a2.shouldTriggerHelpUI("IPH_ExploreSitesTile")) {
                C1175Tf1 tf1 = new C1175Tf1(ui1.getContext(), (View) ui1, string, string2, true, (C4390qK0) new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(ui1), C0283Ep.h().d());
                tf1.e(true);
                View findViewById = ui1.findViewById(R.id.tile_view_highlight);
                if (findViewById != null) {
                    AbstractC3628lu1.a(findViewById, HI0.b(findViewById.getContext(), new BM(), new EI0(null)));
                    tf1.I.P.b(new C5923zM(findViewById, a2));
                    tf1.f();
                }
            }
        }
    }
}
