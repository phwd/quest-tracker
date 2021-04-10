package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.night_mode.settings.RadioButtonGroupThemePreference;
import org.chromium.chrome.browser.night_mode.settings.ThemeSettingsFragment;

/* renamed from: Ug1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1239Ug1 implements XE0 {
    public final PU0 F;
    public final RadioButtonGroupThemePreference G;

    public C1239Ug1(PU0 pu0, RadioButtonGroupThemePreference radioButtonGroupThemePreference) {
        this.F = pu0;
        this.G = radioButtonGroupThemePreference;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        PU0 pu0 = this.F;
        RadioButtonGroupThemePreference radioButtonGroupThemePreference = this.G;
        int i = ThemeSettingsFragment.G0;
        if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting")) {
            pu0.m("darken_websites_enabled", radioButtonGroupThemePreference.z0.isChecked());
        }
        pu0.n("ui_theme_setting", ((Integer) obj).intValue());
        return true;
    }
}
