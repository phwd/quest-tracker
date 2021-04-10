package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.language.settings.LanguageSettings;

/* renamed from: R60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R60 implements YE0 {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ int H;
    public final /* synthetic */ LanguageSettings I;

    public R60(LanguageSettings languageSettings, int i, int i2, int i3) {
        this.I = languageSettings;
        this.F = i;
        this.G = i2;
        this.H = i3;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        T60.e(this.F);
        LanguageSettings languageSettings = this.I;
        int i = this.G;
        int i2 = this.H;
        int i3 = LanguageSettings.G0;
        languageSettings.n1(i, i2);
        return true;
    }
}
