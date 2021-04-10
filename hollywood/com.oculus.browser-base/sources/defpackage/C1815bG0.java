package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: bG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1815bG0 implements YE0 {
    public final PrivacySettings F;

    public C1815bG0(PrivacySettings privacySettings) {
        this.F = privacySettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.n1();
    }
}
