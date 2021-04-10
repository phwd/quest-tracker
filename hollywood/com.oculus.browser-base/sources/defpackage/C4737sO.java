package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: sO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4737sO implements UE0 {
    @Override // defpackage.UE0
    public void a() {
        if (!N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "ntp_snippets.enable")) {
            AbstractC1220Ua0.f("FeedFeatures", "Disabling Feed because of policy.", new Object[0]);
            AbstractC4907tO.f11341a = true;
        }
    }
}
