package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: wy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5514wy0 implements XE0 {
    public final PasswordSettings F;

    public C5514wy0(PasswordSettings passwordSettings) {
        this.F = passwordSettings;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.n1(obj);
    }
}
