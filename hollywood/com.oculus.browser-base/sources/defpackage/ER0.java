package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsSettings;

/* renamed from: ER0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ER0 implements XE0 {
    public final SecureDnsSettings F;

    public ER0(SecureDnsSettings secureDnsSettings) {
        this.F = secureDnsSettings;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.k1(obj);
    }
}
