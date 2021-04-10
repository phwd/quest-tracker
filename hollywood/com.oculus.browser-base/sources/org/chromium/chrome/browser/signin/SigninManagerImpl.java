package org.chromium.chrome.browser.signin;

import J.N;
import android.accounts.Account;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.signin.AccountTrackerService;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountId;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.IdentityMutator;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SigninManagerImpl extends AbstractC5779yZ implements U1, SigninManager {
    public long F;
    public final AccountTrackerService G;
    public final IdentityManager H;
    public final IdentityMutator I;

    /* renamed from: J  reason: collision with root package name */
    public final C4858t6 f10762J;
    public final YM K;
    public final C1322Vq0 L = new C1322Vq0();
    public final C1322Vq0 M = new C1322Vq0();
    public List N = new ArrayList();
    public boolean O;
    public boolean P = true;
    public C3559lW0 Q;
    public C3730mW0 R;

    public SigninManagerImpl(long j, AccountTrackerService accountTrackerService, IdentityManager identityManager, IdentityMutator identityMutator, C4858t6 t6Var, YM ym) {
        Object obj = ThreadUtils.f10596a;
        this.F = j;
        this.G = accountTrackerService;
        this.H = identityManager;
        this.I = identityMutator;
        this.f10762J = t6Var;
        this.K = ym;
        this.O = N.Mo0prJ3k(j);
        accountTrackerService.a(this);
        identityManager.b.b(this);
        l();
        if (!N.M09VlOh_("MobileIdentityConsistency") && identityManager.b(0) != null && identityManager.b(1) == null) {
            AbstractC1220Ua0.f("SigninManager", "Rolling back MobileIdentityConsistency: signing out.", new Object[0]);
            u(13);
            N.MZVBbWbb(this.F);
        }
    }

    public static SigninManager create(long j, AccountTrackerService accountTrackerService, IdentityManager identityManager, IdentityMutator identityMutator) {
        return new SigninManagerImpl(j, accountTrackerService, identityManager, identityMutator, C4858t6.c(), YM.f9268a);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public boolean A() {
        return !this.O;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public boolean B() {
        if (AbstractC3153j7.f()) {
            return false;
        }
        YM ym = this.K;
        ContextUtils.getApplicationContext();
        Objects.requireNonNull(ym);
        return false;
    }

    @Override // defpackage.U1
    public void E() {
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public String G() {
        return N.MM6ImjTk(this.F);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public String J(String str) {
        return N.MiQjxiSl(str);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void K(Runnable runnable) {
        Object obj = ThreadUtils.f10596a;
        if (o()) {
            this.N.add(runnable);
        } else {
            PostTask.b(Zo1.f9374a, runnable, 0);
        }
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void M(AbstractC2193dW0 dw0) {
        this.M.c(dw0);
    }

    @Override // defpackage.AbstractC5779yZ
    public void R() {
        if (this.H.b(0) != null && this.H.b(1) == null) {
            u(12);
        }
    }

    @Override // defpackage.AbstractC5779yZ
    public void T(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        int a2 = primaryAccountChangeEvent.a(1);
        if (a2 != 0) {
            if (a2 != 1 && a2 == 2) {
                if (this.R == null) {
                    this.R = new C3730mW0(null, true);
                }
                C4072oW0.f10556a.b.p("google.services.username", null);
                U(this.R.b, new RunnableC3217jW0(this));
            }
        } else if (primaryAccountChangeEvent.a(0) == 2) {
            if (this.R == null) {
                this.R = new C3730mW0(null, false);
            }
            U(this.R.b, new RunnableC3388kW0(this));
        }
    }

    public final void U(boolean z, Runnable runnable) {
        C3730mW0 mw0 = this.R;
        boolean z2 = mw0.b;
        AbstractC2705gW0 gw0 = mw0.f10428a;
        if (gw0 != null) {
            gw0.a();
        }
        C4858t6 t6Var = this.f10762J;
        Objects.requireNonNull(t6Var);
        Object obj = ThreadUtils.f10596a;
        t6Var.d = null;
        t6Var.g();
        if (t6Var.f()) {
            t6Var.d();
        }
        if (z) {
            N.MyfLWqOr(this.F, runnable);
        } else {
            N.M3tTsu$h(this.F, runnable);
        }
        this.G.c(true);
    }

    public void V() {
        N.McMy7mwQ(this.I.f10895a, this.Q.e.getId());
        boolean a2 = this.Q.a();
        if (!N.MASdubqY(this.I.f10895a, this.Q.e.getId(), a2 ? 1 : 0)) {
            AbstractC1220Ua0.f("SigninManager", "Failed to set the PrimaryAccount in IdentityManager, aborting signin", new Object[0]);
            C3559lW0 lw0 = this.Q;
            this.Q = null;
            X();
            AbstractC2363eW0 ew0 = lw0.c;
            if (ew0 != null) {
                ew0.b();
            }
            N.MREkQQeM(this.F);
            Y();
            return;
        }
        if (this.Q.a()) {
            C4072oW0.f10556a.b.p("google.services.username", this.Q.e.getEmail());
            C4858t6 t6Var = this.f10762J;
            Account b = V1.b(this.Q.e.getEmail());
            Objects.requireNonNull(t6Var);
            Object obj = ThreadUtils.f10596a;
            t6Var.d = b;
            t6Var.g();
            if (t6Var.f()) {
                t6Var.d();
            }
            ProfileSyncService b2 = ProfileSyncService.b();
            boolean z = !((HashSet) ProfileSyncService.p(N.M_gH1Vgj(b2.e, b2))).isEmpty();
            if (!N.M09VlOh_("MobileIdentityConsistency") || z) {
                this.f10762J.b();
            }
            AbstractC3535lK0.a("Signin_Signin_Succeed");
            AbstractC3364kK0.g("Signin.SigninCompletedAccessPoint", this.Q.f10349a.intValue(), 34);
            AbstractC3364kK0.g("Signin.SigninReason", 0, 7);
        }
        AbstractC2363eW0 ew02 = this.Q.c;
        if (ew02 != null) {
            ew02.a();
        }
        this.Q = null;
        X();
        Y();
        Iterator it = this.L.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2534fW0) uq0.next()).b();
            } else {
                return;
            }
        }
    }

    public void W() {
        AbstractC2705gW0 gw0 = this.R.f10428a;
        this.R = null;
        if (gw0 != null) {
            gw0.b();
        }
        X();
        Iterator it = this.L.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2534fW0) uq0.next()).o();
            } else {
                return;
            }
        }
    }

    public final void X() {
        Object obj = ThreadUtils.f10596a;
        for (Runnable runnable : this.N) {
            PostTask.b(Zo1.f9374a, runnable, 0);
        }
        this.N.clear();
    }

    public final void Y() {
        PostTask.b(Zo1.f9374a, new RunnableC2876hW0(this), 0);
    }

    public final void Z() {
        C3559lW0 lw0 = this.Q;
        if (lw0 == null) {
            AbstractC1220Ua0.f("SigninManager", "Ignoring sign in progress request as no pending sign in.", new Object[0]);
            return;
        }
        lw0.e = this.H.a(lw0.b.name);
        if (this.Q.a()) {
            N.Mn1Rv$d9(this.F, this.Q.e, new RunnableC3047iW0(this));
            return;
        }
        V();
    }

    public final void a0(C3559lW0 lw0) {
        if (lw0.b == null) {
            AbstractC1220Ua0.f("SigninManager", "Ignoring sign-in request due to null account.", new Object[0]);
            AbstractC2363eW0 ew0 = lw0.c;
            if (ew0 != null) {
                ew0.b();
            }
        } else if (this.Q != null) {
            AbstractC1220Ua0.f("SigninManager", "Ignoring sign-in request as another sign-in request is pending.", new Object[0]);
            AbstractC2363eW0 ew02 = lw0.c;
            if (ew02 != null) {
                ew02.b();
            }
        } else if (this.P) {
            AbstractC1220Ua0.f("SigninManager", "Ignoring sign-in request until the First Run check completes.", new Object[0]);
            AbstractC2363eW0 ew03 = lw0.c;
            if (ew03 != null) {
                ew03.b();
            }
        } else {
            this.Q = lw0;
            Y();
            if (this.G.b()) {
                Z();
            } else {
                this.Q.d = true;
            }
        }
    }

    public void destroy() {
        this.H.b.c(this);
        AccountTrackerService accountTrackerService = this.G;
        Objects.requireNonNull(accountTrackerService);
        Object obj = ThreadUtils.f10596a;
        accountTrackerService.e.c(this);
        this.F = 0;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public IdentityManager f() {
        return this.H;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void g(CoreAccountInfo coreAccountInfo, AbstractC2363eW0 ew0) {
        a0(new C3559lW0(null, CoreAccountInfo.a(coreAccountInfo), ew0));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void h(int i, AbstractC2705gW0 gw0, boolean z) {
        this.R = new C3730mW0(gw0, z || G() != null);
        N.Mw3X2cb0(this.I.f10895a, i, 2);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public boolean i() {
        if (this.P || this.Q != null || !this.O || this.H.b(1) != null) {
            return false;
        }
        B();
        return false;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void j(String str, Callback callback) {
        N.M7ZP5quR(this.F, (AccountInfo) N.MRQQkZGI(this.H.f10894a, str), callback);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void k() {
        this.P = false;
        i();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void l() {
        CoreAccountId coreAccountId;
        IdentityMutator identityMutator = this.I;
        CoreAccountInfo b = this.H.b(0);
        if (b == null) {
            coreAccountId = null;
        } else {
            coreAccountId = b.getId();
        }
        N.McMy7mwQ(identityMutator.f10895a, coreAccountId);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void m(AbstractC2534fW0 fw0) {
        this.L.b(fw0);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public boolean o() {
        Object obj = ThreadUtils.f10596a;
        return (this.Q == null && this.R == null) ? false : true;
    }

    public final void onSigninAllowedByPolicyChanged(boolean z) {
        this.O = z;
        Y();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public boolean p() {
        return N.MRa0T_Mz(this.F);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void q(AbstractC2193dW0 dw0) {
        this.M.b(dw0);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    @Deprecated
    public void t(int i, Account account, AbstractC2363eW0 ew0) {
        a0(new C3559lW0(Integer.valueOf(i), account, ew0));
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void u(int i) {
        h(i, null, false);
    }

    @Override // defpackage.U1
    public void v() {
        C3559lW0 lw0 = this.Q;
        if (lw0 != null && lw0.d) {
            lw0.d = false;
            Z();
        }
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager
    public void z(AbstractC2534fW0 fw0) {
        this.L.c(fw0);
    }
}
