package org.chromium.chrome.browser.sync.settings;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninPromoUtil;
import org.chromium.chrome.browser.signin.ui.PersonalizedSigninPromoView;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncPromoPreference extends Preference implements AbstractC2193dW0, VG0, AbstractC3526lH0, W1 {
    public final WG0 t0;
    public final AccountManagerFacade u0 = AccountManagerFacadeProvider.getInstance();
    public int v0 = 0;
    public Runnable w0;
    public C5264vW0 x0;

    public SyncPromoPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f40630_resource_name_obfuscated_RES_2131624372;
        this.t0 = WG0.V(context, 0);
        W(false);
    }

    @Override // androidx.preference.Preference
    public void A() {
        Z();
        this.u0.m(this);
        C5949zZ.a().d(Profile.b()).M(this);
        this.t0.Y(this);
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.q(this);
        }
    }

    @Override // defpackage.VG0
    public void D(String str) {
        c0();
    }

    public final void a0(int i) {
        if (this.v0 != i) {
            this.v0 = i;
            this.w0.run();
        }
        Q(false);
        W(true);
        if (this.x0 == null) {
            this.x0 = new C5264vW0(3, BV0.a());
        }
        s();
    }

    public final void b0() {
        if (this.v0 != 0) {
            this.v0 = 0;
            this.w0.run();
        }
        this.x0 = null;
        W(false);
    }

    @Override // defpackage.AbstractC2193dW0
    public void c() {
        c0();
    }

    public final void c0() {
        if (C5949zZ.a().d(Profile.b()).A()) {
            b0();
            return;
        }
        if (!NU0.f8549a.d("settings_personalized_signin_promo_dismissed", false) && C5264vW0.b(3)) {
            IdentityManager c = C5949zZ.a().c(Profile.b());
            if (c.b(0) == null) {
                a0(1);
                return;
            } else if (c.b(1) == null) {
                a0(2);
                return;
            }
        }
        b0();
    }

    @Override // defpackage.W1
    public void e() {
        c0();
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        c0();
    }

    @Override // androidx.preference.Preference
    public void v() {
        super.v();
        this.u0.a(this);
        C5949zZ.a().d(Profile.b()).q(this);
        this.t0.U(this);
        XQ.c();
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.a(this);
        }
        c0();
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        if (this.x0 != null) {
            PersonalizedSigninPromoView personalizedSigninPromoView = (PersonalizedSigninPromoView) tf0.x(R.id.signin_promo_view_container);
            if (this.v0 == 1) {
                SigninPromoUtil.a(this.x0, this.t0, personalizedSigninPromoView, new C2295e51(this));
            } else {
                SigninPromoUtil.b(this.x0, this.t0, personalizedSigninPromoView, new C2466f51(this));
            }
        }
    }
}
