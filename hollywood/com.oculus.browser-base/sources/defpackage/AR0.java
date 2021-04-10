package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference;

/* renamed from: AR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class AR0 implements Runnable {
    public final SecureDnsProviderPreference F;
    public final String G;

    public AR0(SecureDnsProviderPreference secureDnsProviderPreference, String str) {
        this.F = secureDnsProviderPreference;
        this.G = str;
    }

    public void run() {
        SecureDnsProviderPreference secureDnsProviderPreference = this.F;
        String str = this.G;
        Objects.requireNonNull(secureDnsProviderPreference);
        for (String str2 : N.MqXWPCd8(str)) {
            if (N.MdHiB3Rh(str2)) {
                return;
            }
        }
        secureDnsProviderPreference.C0.post(new BR0(secureDnsProviderPreference, str));
    }
}
