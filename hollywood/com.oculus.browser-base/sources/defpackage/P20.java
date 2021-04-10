package defpackage;

import J.N;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: P20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P20 extends AbstractC2574fl {
    public int d;

    public P20(String str, String str2, int i) {
        super(str, str2, 2, null);
        this.d = i;
    }

    @Override // defpackage.AbstractC2574fl
    public void a() {
        NU0.f8549a.n(b(), N.M37SqSAy(this.f9946a, this.b, this.d));
    }

    public int c() {
        String b = b();
        int i = this.d;
        Integer num = (Integer) CachedFeatureFlags.e.get(b);
        if (num == null) {
            num = Integer.valueOf(NU0.f8549a.f(b, i));
            CachedFeatureFlags.e.put(b, num);
        }
        return num.intValue();
    }
}
