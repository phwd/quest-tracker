package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.homepage.settings.HomepageSettings;

/* renamed from: TX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class TX implements XE0 {
    public final HomepageSettings F;

    public TX(HomepageSettings homepageSettings) {
        this.F = homepageSettings;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.l1(obj);
    }
}
