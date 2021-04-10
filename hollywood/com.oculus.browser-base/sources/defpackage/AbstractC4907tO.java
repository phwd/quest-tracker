package defpackage;

import J.N;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: tO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4907tO {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f11341a;
    public static PrefChangeRegistrar b;

    public static boolean a() {
        if (f11341a) {
            return false;
        }
        if (b == null) {
            PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
            b = prefChangeRegistrar;
            prefChangeRegistrar.f10748a.put("ntp_snippets.enable", new C4737sO());
            N.Mrf8X6ah(prefChangeRegistrar.b, prefChangeRegistrar, "ntp_snippets.enable");
        }
        if (!f11341a) {
            f11341a = !N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "ntp_snippets.enable");
        }
        return !f11341a;
    }
}
