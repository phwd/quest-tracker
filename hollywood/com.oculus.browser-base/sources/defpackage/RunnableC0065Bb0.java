package defpackage;

import J.N;
import org.chromium.chrome.browser.settings.MainSettings;

/* renamed from: Bb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0065Bb0 implements Runnable {
    public final MainSettings F;

    public RunnableC0065Bb0(MainSettings mainSettings) {
        this.F = mainSettings;
    }

    public void run() {
        MainSettings mainSettings = this.F;
        boolean z = !(mainSettings.I0.v0 == 1);
        mainSettings.f1(N.M09VlOh_("MobileIdentityConsistency") ? "account_and_google_services_section" : "account_section").W(z);
        mainSettings.J0.W(z);
    }
}
