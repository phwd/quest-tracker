package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: XF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XF0 implements YE0 {
    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        String[] strArr = PrivacySettings.G0;
        preference.j().putInt("SafeBrowsingSettingsFragment.AccessPoint", 1);
        return false;
    }
}
