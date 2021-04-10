package defpackage;

import J.N;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: Xi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1426Xi extends AbstractC2574fl {
    public boolean d;

    public C1426Xi(String str, String str2, boolean z) {
        super(str, str2, 1, null);
        this.d = z;
    }

    @Override // defpackage.AbstractC2574fl
    public void a() {
        NU0.f8549a.m(b(), N.M6bsIDpc(this.f9946a, this.b, this.d));
    }

    public boolean c() {
        return CachedFeatureFlags.b(b(), this.d);
    }
}
