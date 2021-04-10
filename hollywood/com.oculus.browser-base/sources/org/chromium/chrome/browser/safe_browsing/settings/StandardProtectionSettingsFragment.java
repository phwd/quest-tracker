package org.chromium.chrome.browser.safe_browsing.settings;

import J.N;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class StandardProtectionSettingsFragment extends SafeBrowsingSettingsFragmentBase implements XE0 {
    public ChromeSwitchPreference G0;
    public ChromeSwitchPreference H0;
    public final AbstractC1528Zb0 I0 = new C1588a01(this);
    public final PrefService J0 = Wr1.a(Profile.b());

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        String str = preference.Q;
        if ("extended_reporting".equals(str)) {
            N.MjGeUNkF(((Boolean) obj).booleanValue());
            return true;
        } else if (!"password_leak_detection".equals(str)) {
            return true;
        } else {
            PrefService prefService = this.J0;
            N.Mf2ABpoH(prefService.f10883a, "profile.password_manager_leak_detection", ((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public int k1() {
        return R.xml.f76420_resource_name_obfuscated_RES_2132213798;
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public void l1(Bundle bundle, String str) {
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("extended_reporting");
        this.G0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = this;
        AbstractC1528Zb0 zb0 = this.I0;
        chromeSwitchPreference.B0 = zb0;
        AbstractC1865bc0.b(zb0, chromeSwitchPreference);
        ChromeSwitchPreference chromeSwitchPreference2 = (ChromeSwitchPreference) f1("password_leak_detection");
        this.H0 = chromeSwitchPreference2;
        chromeSwitchPreference2.f9480J = this;
        AbstractC1528Zb0 zb02 = this.I0;
        chromeSwitchPreference2.B0 = zb02;
        AbstractC1865bc0.b(zb02, chromeSwitchPreference2);
        int MdyQjr8h = N.MdyQjr8h();
        boolean z = false;
        boolean z2 = MdyQjr8h == 2;
        boolean z3 = MdyQjr8h == 1;
        boolean z4 = z2 || (z3 && N.MWJZTkWR());
        this.G0.K(z3 && !this.I0.b(this.G0));
        this.G0.a0(z4);
        boolean MiM2m7HY = N.MiM2m7HY();
        boolean MzIXnlkD = N.MzIXnlkD(this.J0.f10883a, "profile.password_manager_leak_detection");
        boolean b = this.I0.b(this.H0);
        boolean z5 = z2 || (z3 && MzIXnlkD);
        boolean z6 = z5 && MiM2m7HY;
        ChromeSwitchPreference chromeSwitchPreference3 = this.H0;
        if (z3 && MiM2m7HY && !b) {
            z = true;
        }
        chromeSwitchPreference3.K(z);
        this.H0.a0(z6);
        if (z5 && !MiM2m7HY) {
            this.H0.S(R.string.f58120_resource_name_obfuscated_RES_2131953129);
        }
    }
}
