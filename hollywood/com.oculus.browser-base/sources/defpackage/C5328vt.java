package defpackage;

import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: vt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5328vt extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Profile profile = (Profile) obj;
        if (profile != null) {
            Um1.a(profile).notifyEvent("started_from_main_intent");
        }
    }
}
