package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: yy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5854yy0 implements YE0 {
    public final PasswordSettings F;

    public C5854yy0(PasswordSettings passwordSettings) {
        this.F = passwordSettings;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.p1();
    }
}
