package org.chromium.chrome.browser.language.settings;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.preference.PreferenceCategory;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LanguageSettings extends AbstractC2324eF0 implements AbstractC5019u3 {
    public static final /* synthetic */ int G0 = 0;

    public static PrefService m1() {
        return Wr1.a(Profile.b());
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            String stringExtra = intent.getStringExtra("AddLanguageFragment.SelectedLanguages");
            T60 a2 = T60.a();
            Objects.requireNonNull(a2);
            N.Me60Lv4_(stringExtra, true);
            a2.c();
            T60.d(2);
        } else if (i == 2 && i2 == -1) {
            String stringExtra2 = intent.getStringExtra("AddLanguageFragment.SelectedLanguages");
            ((LanguageItemPickerPreference) f1("app_language_preference")).a0(stringExtra2);
            NU0.f8549a.p("Chrome.Language.ApplicationOverrideLanguage", stringExtra2);
            Boolean bool = BundleUtils.f10583a;
            T60.d(9);
        } else if (i == 3 && i2 == -1) {
            String stringExtra3 = intent.getStringExtra("AddLanguageFragment.SelectedLanguages");
            ((LanguageItemPickerPreference) f1("translate_settings_target_language")).a0(stringExtra3);
            N.MMJjRfp9(stringExtra3);
            T60.d(10);
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f53850_resource_name_obfuscated_RES_2131952702);
        if (N.M09VlOh_("DetailedLanguageSettings")) {
            l1();
        } else {
            k1();
        }
        T60.e(0);
    }

    public final void k1() {
        AbstractC2870hT0.a(this, R.xml.f76250_resource_name_obfuscated_RES_2132213781);
        LanguageListPreference languageListPreference = (LanguageListPreference) f1("preferred_languages");
        languageListPreference.w0 = this;
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("translate_switch");
        chromeSwitchPreference.a0(N.MzIXnlkD(m1().f10883a, "translate.enabled"));
        chromeSwitchPreference.f9480J = new O60(this, languageListPreference);
        M60 m60 = new M60();
        chromeSwitchPreference.B0 = m60;
        AbstractC1865bc0.b(m60, chromeSwitchPreference);
    }

    public final void l1() {
        AbstractC2870hT0.a(this, R.xml.f76240_resource_name_obfuscated_RES_2132213780);
        String str = AbstractC0456Hk.f8178a.b;
        ((PreferenceCategory) f1("app_language_section")).V(I().getString(R.string.f46940_resource_name_obfuscated_RES_2131952011, str));
        LanguageItemPickerPreference languageItemPickerPreference = (LanguageItemPickerPreference) f1("app_language_preference");
        languageItemPickerPreference.a0(NU0.f8549a.i("Chrome.Language.ApplicationOverrideLanguage", null));
        languageItemPickerPreference.y0 = true;
        languageItemPickerPreference.b0();
        languageItemPickerPreference.K = new R60(this, 3, 1, 2);
        LanguageListPreference languageListPreference = (LanguageListPreference) f1("content_languages_preference");
        languageListPreference.w0 = this;
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("translate_switch");
        chromeSwitchPreference.a0(N.MzIXnlkD(m1().f10883a, "translate.enabled"));
        ((PreferenceCategory) f1("translation_settings_section")).A0 = new P60(this);
        LanguageItemPickerPreference languageItemPickerPreference2 = (LanguageItemPickerPreference) f1("translate_settings_target_language");
        chromeSwitchPreference.f9480J = new Q60(this, languageListPreference, languageItemPickerPreference2);
        N60 n60 = new N60();
        chromeSwitchPreference.B0 = n60;
        AbstractC1865bc0.b(n60, chromeSwitchPreference);
        languageItemPickerPreference2.a0(N.MMKf4EpW());
        languageItemPickerPreference2.K = new R60(this, 5, 2, 3);
    }

    public final void n1(int i, int i2) {
        Activity u = u();
        String name = AddLanguageFragment.class.getName();
        Intent intent = new Intent();
        intent.setClass(u, XS0.class);
        if (!(u instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("AddLanguageFragment.LanguageOptions", i);
        d1(intent, i2);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void o0() {
        this.i0 = true;
        T60.f8939a = null;
    }
}
