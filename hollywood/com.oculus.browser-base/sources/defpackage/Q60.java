package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.language.settings.LanguageItemPickerPreference;
import org.chromium.chrome.browser.language.settings.LanguageListPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;

/* renamed from: Q60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q60 implements XE0 {
    public final /* synthetic */ LanguageListPreference F;
    public final /* synthetic */ LanguageItemPickerPreference G;

    public Q60(LanguageSettings languageSettings, LanguageListPreference languageListPreference, LanguageItemPickerPreference languageItemPickerPreference) {
        this.F = languageListPreference;
        this.G = languageItemPickerPreference;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        N.Mf2ABpoH(LanguageSettings.m1().f10883a, "translate.enabled", booleanValue);
        this.F.v0.u();
        this.G.W(booleanValue);
        T60.d(booleanValue ? 5 : 4);
        return true;
    }
}
