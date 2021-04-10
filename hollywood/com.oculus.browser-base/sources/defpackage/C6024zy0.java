package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: zy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C6024zy0 implements YE0 {
    public final PasswordSettings F;

    public C6024zy0(PasswordSettings passwordSettings) {
        this.F = passwordSettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.s1();
    }
}
