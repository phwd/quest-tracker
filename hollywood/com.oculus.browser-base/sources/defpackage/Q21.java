package defpackage;

import J.N;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: Q21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q21 extends AbstractC2574fl {
    public final String d;

    public Q21(String str, String str2, String str3) {
        super(str, str2, 0, null);
        this.d = str3;
    }

    @Override // defpackage.AbstractC2574fl
    public void a() {
        String MMltG$kc = N.MMltG$kc(this.f9946a, this.b);
        PU0 pu0 = NU0.f8549a;
        String b = b();
        if (MMltG$kc.isEmpty()) {
            MMltG$kc = this.d;
        }
        pu0.p(b, MMltG$kc);
    }

    public String c() {
        String b = b();
        String str = this.d;
        String str2 = (String) CachedFeatureFlags.d.get(b);
        if (str2 != null) {
            return str2;
        }
        String i = NU0.f8549a.i(b, str);
        CachedFeatureFlags.d.put(b, i);
        return i;
    }
}
