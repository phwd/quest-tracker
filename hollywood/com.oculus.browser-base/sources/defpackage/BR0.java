package defpackage;

import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference;

/* renamed from: BR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BR0 implements Runnable {
    public final SecureDnsProviderPreference F;
    public final String G;

    public BR0(SecureDnsProviderPreference secureDnsProviderPreference, String str) {
        this.F = secureDnsProviderPreference;
        this.G = str;
    }

    public void run() {
        SecureDnsProviderPreference secureDnsProviderPreference = this.F;
        if (secureDnsProviderPreference.E0.b.contentEquals(this.G)) {
            secureDnsProviderPreference.D0.w(secureDnsProviderPreference.v0);
        }
    }
}
