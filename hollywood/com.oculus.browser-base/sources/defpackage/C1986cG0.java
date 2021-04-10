package defpackage;

import J.N;
import androidx.preference.Preference;
import java.util.Objects;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* renamed from: cG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1986cG0 extends AbstractC0896Or {
    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        String[] strArr = PrivacySettings.G0;
        if (!"preload_pages".equals(preference.Q)) {
            return false;
        }
        Objects.requireNonNull(WF0.a());
        return N.MpDwU7Ec();
    }
}
