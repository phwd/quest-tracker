package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.WebSigninBridge;
import org.chromium.components.signin.base.CoreAccountId;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: r1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4502r1 extends AbstractC2032cb {
    public final /* synthetic */ C4673s1 i;

    public C4502r1(C4673s1 s1Var) {
        this.i = s1Var;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        C4673s1 s1Var = this.i;
        return s1Var.f11244J.f(s1Var.L);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String str = (String) obj;
        CoreAccountInfo coreAccountInfo = new CoreAccountInfo(new CoreAccountId(str), this.i.L, str);
        C4673s1 s1Var = this.i;
        AbstractC5863z1 z1Var = s1Var.F;
        C4332q1 q1Var = new C4332q1(s1Var);
        D1 d1 = (D1) z1Var;
        if (d1.g.b(0) != null) {
            d1.c();
            d1.f.u(4);
        }
        d1.i = q1Var;
        Sx1 sx1 = d1.d;
        Profile b = Profile.b();
        Objects.requireNonNull(sx1);
        d1.h = new WebSigninBridge(b, coreAccountInfo, d1, null);
        d1.f.g(coreAccountInfo, new B1(d1));
    }
}
