package defpackage;

import J.N;
import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninPromoUtil;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* renamed from: gP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2690gP extends AbstractC2941ht0 {
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9995J;
    public boolean K;
    public final C5772yV0 L;
    public final C5264vW0 M;
    public final WG0 N;
    public final /* synthetic */ C2861hP O;

    public C2690gP(C2861hP hPVar, SigninManager signinManager) {
        this.O = hPVar;
        Context applicationContext = ContextUtils.getApplicationContext();
        this.I = signinManager.i() && !signinManager.f().c();
        this.f9995J = AccountManagerFacadeProvider.getInstance().d();
        x();
        this.N = WG0.V(applicationContext, 0);
        this.M = new C5264vW0(20, BV0.a());
        this.L = new C5772yV0(this, signinManager, null);
        w();
    }

    @Override // defpackage.AbstractC2941ht0
    public /* bridge */ /* synthetic */ int s() {
        return 8;
    }

    @Override // defpackage.AbstractC2941ht0
    public void t(PX0 px0) {
    }

    @Override // defpackage.AbstractC2941ht0
    public void u(boolean z) {
        if (this.H != z) {
            super.u(z);
            this.O.F.c(z, null);
            w();
        }
    }

    public boolean v() {
        if (!N.M09VlOh_("MobileIdentityConsistency")) {
            return false;
        }
        IdentityManager c = C5949zZ.a().c(Profile.b());
        if (c.b(0) == null || c.b(1) != null) {
            return false;
        }
        return true;
    }

    public final void w() {
        if (this.H) {
            if (v()) {
                SigninPromoUtil.b(this.M, this.N, this.O.F.a(), null);
            } else {
                SigninPromoUtil.a(this.M, this.N, this.O.F.a(), null);
            }
        }
    }

    public final void x() {
        boolean z = true;
        boolean z2 = this.I && this.K && this.f9995J;
        boolean z3 = v() && this.K && this.f9995J;
        if (!z2 && !z3) {
            z = false;
        }
        if (this.H != z) {
            super.u(z);
            this.O.F.c(z, null);
            w();
        }
    }
}
