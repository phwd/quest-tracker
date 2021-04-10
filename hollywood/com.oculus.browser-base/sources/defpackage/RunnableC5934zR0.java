package defpackage;

import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference;

/* renamed from: zR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5934zR0 implements Runnable {
    public final SecureDnsProviderPreference F;

    public RunnableC5934zR0(SecureDnsProviderPreference secureDnsProviderPreference) {
        this.F = secureDnsProviderPreference;
    }

    public void run() {
        SecureDnsProviderPreference secureDnsProviderPreference = this.F;
        String str = secureDnsProviderPreference.E0.b;
        if (!str.isEmpty()) {
            CR0 cr0 = secureDnsProviderPreference.E0;
            if (cr0.c && cr0.f7809a) {
                new Thread(new AR0(secureDnsProviderPreference, str)).start();
            }
        }
    }
}
