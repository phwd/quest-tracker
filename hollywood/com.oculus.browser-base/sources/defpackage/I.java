package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.accessibility.settings.AccessibilitySettings;

/* renamed from: I  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class I implements YE0 {
    public final AccessibilitySettings F;

    public I(AccessibilitySettings accessibilitySettings) {
        this.F = accessibilitySettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.k1();
    }
}
