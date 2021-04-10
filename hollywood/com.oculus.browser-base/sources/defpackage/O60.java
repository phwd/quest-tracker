package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.language.settings.LanguageListPreference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;

/* renamed from: O60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O60 implements XE0 {
    public final /* synthetic */ LanguageListPreference F;

    public O60(LanguageSettings languageSettings, LanguageListPreference languageListPreference) {
        this.F = languageListPreference;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        N.Mf2ABpoH(LanguageSettings.m1().f10883a, "translate.enabled", booleanValue);
        this.F.v0.u();
        T60.d(booleanValue ? 5 : 4);
        return true;
    }
}
