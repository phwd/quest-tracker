package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: i0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2962i0 implements AbstractC4371qE {
    public final Y2 F;
    public TabImpl G;

    public C2962i0(M2 m2, C1595a3 a3Var, C1343Wa1 wa1) {
        this.F = new C2791h0(this, a3Var, wa1);
        m2.a(this);
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        this.F.destroy();
        this.G = null;
    }
}
