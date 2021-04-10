package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsSettings;

/* renamed from: FR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class FR0 implements XE0 {
    public final SecureDnsSettings F;

    public FR0(SecureDnsSettings secureDnsSettings) {
        this.F = secureDnsSettings;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.l1(obj);
    }
}
