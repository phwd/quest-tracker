package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper$FaviconImageCallback;
import org.chromium.url.GURL;

/* renamed from: lO  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3542lO {

    /* renamed from: a  reason: collision with root package name */
    public long f10342a = N.MUcnJuRZ();

    public static boolean a(String str) {
        GURL a2 = AbstractC1911br1.a(str);
        if (!a2.b) {
            return false;
        }
        return AbstractC5154ur1.b.contains(a2.g());
    }

    public void b() {
        N.Mz5mgjYL(this.f10342a);
        this.f10342a = 0;
    }

    public boolean c(Profile profile, String str, int i, FaviconHelper$FaviconImageCallback faviconHelper$FaviconImageCallback) {
        return N.MBZyBYDK(this.f10342a, profile, str, i, faviconHelper$FaviconImageCallback);
    }
}
