package org.chromium.chrome.browser.night_mode.settings;

import android.os.Build;
import android.os.Bundle;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThemeSettingsFragment extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        if (Build.VERSION.SDK_INT == 27) {
            AbstractC2417ep1.l(u().getWindow().getDecorView(), I().getBoolean(R.bool.f9560_resource_name_obfuscated_RES_2131034119));
        }
        i1(null);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76440_resource_name_obfuscated_RES_2132213800);
        u().setTitle(R.string.f63350_resource_name_obfuscated_RES_2131953652);
        PU0 pu0 = NU0.f8549a;
        RadioButtonGroupThemePreference radioButtonGroupThemePreference = (RadioButtonGroupThemePreference) f1("ui_theme_pref");
        int a2 = AbstractC3612lp0.a();
        boolean d = pu0.d("darken_websites_enabled", false);
        radioButtonGroupThemePreference.t0 = a2;
        radioButtonGroupThemePreference.y0 = d;
        radioButtonGroupThemePreference.f9480J = new C1239Ug1(pu0, radioButtonGroupThemePreference);
    }
}
