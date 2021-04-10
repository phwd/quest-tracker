package defpackage;

import android.app.Activity;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: yX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5776yX0 implements Runnable {
    public final SingleWebsiteSettings F;

    public RunnableC5776yX0(SingleWebsiteSettings singleWebsiteSettings) {
        this.F = singleWebsiteSettings;
    }

    public void run() {
        SingleWebsiteSettings singleWebsiteSettings = this.F;
        Activity u = singleWebsiteSettings.u();
        if (u != null && !u.isFinishing()) {
            singleWebsiteSettings.B1("clear_data");
            if (!singleWebsiteSettings.r1()) {
                singleWebsiteSettings.B1("site_usage");
            }
            Preference f1 = singleWebsiteSettings.f1("chooser_permission_list");
            if (f1 != null) {
                ChromeImageViewPreference chromeImageViewPreference = (ChromeImageViewPreference) f1;
                AbstractC1528Zb0 zb0 = chromeImageViewPreference.t0;
                if (!(zb0 != null && (zb0.d(chromeImageViewPreference) || chromeImageViewPreference.t0.a(chromeImageViewPreference)))) {
                    PreferenceScreen preferenceScreen = singleWebsiteSettings.z0.g;
                    preferenceScreen.g0(f1);
                    preferenceScreen.u();
                }
            }
            singleWebsiteSettings.N0 = 0;
            if (singleWebsiteSettings.O0 > 0) {
                Context x = singleWebsiteSettings.x();
                C1184Ti1.b(x, x.getString(R.string.f54380_resource_name_obfuscated_RES_2131952755), 1).b.show();
            }
            if (!singleWebsiteSettings.q1() && !singleWebsiteSettings.r1() && singleWebsiteSettings.u() != null) {
                singleWebsiteSettings.u().finish();
            }
        }
    }
}
