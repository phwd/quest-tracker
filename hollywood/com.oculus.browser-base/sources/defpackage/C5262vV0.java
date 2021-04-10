package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.sync.settings.SignInPreference;

/* renamed from: vV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5262vV0 implements YE0 {
    public final SignInPreference F;

    public C5262vV0(SignInPreference signInPreference) {
        this.F = signInPreference;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.b0();
    }
}
