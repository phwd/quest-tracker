package org.chromium.chrome.browser.notifications.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationSettings extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;
    public Preference H0;
    public ChromeSwitchPreference I0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        boolean z = true;
        this.i0 = true;
        if (this.I0 != null) {
            boolean a2 = AbstractC5056uF0.a();
            boolean d = NU0.f8549a.d("prefetch_notification_enabled", true);
            ChromeSwitchPreference chromeSwitchPreference = this.I0;
            if (!a2 || !d) {
                z = false;
            }
            chromeSwitchPreference.a0(z);
            this.I0.K(a2);
            this.I0.S(a2 ? R.string.f56240_resource_name_obfuscated_RES_2131952941 : R.string.f56250_resource_name_obfuscated_RES_2131952942);
        }
        this.H0.S(AbstractC1154Sy.b(6, N.MJSt3Ocq(Profile.b(), 6)));
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76310_resource_name_obfuscated_RES_2132213787);
        u().setTitle(R.string.f59140_resource_name_obfuscated_RES_2131953231);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("content_suggestions");
        this.I0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = new C1381Wp0();
        Preference f1 = f1("from_websites");
        this.H0 = f1;
        f1.j().putString("category", QX0.p(14));
    }
}
