package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Collections;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SignInPreference extends Preference implements AbstractC2193dW0, VG0, AbstractC3526lH0, W1 {
    public final PrefService t0 = Wr1.a(Profile.b());
    public boolean u0;
    public boolean v0;
    public final WG0 w0;
    public final AccountManagerFacade x0;
    public int y0;

    public SignInPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f36620_resource_name_obfuscated_RES_2131623971;
        this.w0 = new WG0(context, context.getResources().getDimensionPixelSize(R.dimen.f26670_resource_name_obfuscated_RES_2131166286));
        this.x0 = AccountManagerFacadeProvider.getInstance();
        this.y0 = 3;
    }

    @Override // androidx.preference.Preference
    public void A() {
        Z();
        this.x0.m(this);
        C5949zZ.a().d(Profile.b()).M(this);
        this.w0.Y(this);
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.q(this);
        }
    }

    @Override // defpackage.VG0
    public void D(String str) {
        d0();
    }

    public final boolean a0() {
        return BV0.a().b(this.F, 3);
    }

    public final boolean b0() {
        AbstractC1865bc0.e(this.F);
        return true;
    }

    @Override // defpackage.AbstractC2193dW0
    public void c() {
        d0();
    }

    public final void c0(boolean z) {
        if (this.v0 != z) {
            this.v0 = z;
            s();
        }
    }

    public final void d0() {
        if (!C5949zZ.a().d(Profile.b()).A()) {
            CoreAccountInfo m = AbstractC2531fV.m(C5949zZ.a(), 0);
            if (m != null) {
                String email = m.getEmail();
                this.y0 = 3;
                this.w0.Z(Collections.singletonList(email));
                C3522lG W = this.w0.W(email);
                V(W.a());
                T(email);
                this.S = AccountManagementFragment.class.getName();
                N(W.b);
                this.l0 = 0;
                c0(true);
                this.K = null;
                this.u0 = false;
                return;
            }
            this.y0 = 2;
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                U(R.string.f62990_resource_name_obfuscated_RES_2131953616);
            } else {
                U(R.string.f61930_resource_name_obfuscated_RES_2131953510);
            }
            S(R.string.f62120_resource_name_obfuscated_RES_2131953529);
            this.S = null;
            N(AbstractC5544x8.a(this.F, R.drawable.f33530_resource_name_obfuscated_RES_2131231393));
            this.l0 = 0;
            c0(true);
            this.K = new C5432wV0(this);
            if (!this.u0) {
                AbstractC3535lK0.a("Signin_Impression_FromSettings");
            }
            this.u0 = true;
        } else if (N.MrEgF7hX(this.t0.f10883a, "signin.allowed")) {
            this.y0 = 0;
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                U(R.string.f62990_resource_name_obfuscated_RES_2131953616);
            } else {
                U(R.string.f61930_resource_name_obfuscated_RES_2131953510);
            }
            S(R.string.f61940_resource_name_obfuscated_RES_2131953511);
            this.S = null;
            M(R.drawable.f29660_resource_name_obfuscated_RES_2131231006);
            this.l0 = 0;
            c0(false);
            this.K = new C5262vV0(this);
            this.u0 = false;
        } else {
            this.y0 = 1;
            U(R.string.f62110_resource_name_obfuscated_RES_2131953528);
            T(null);
            this.S = null;
            N(AbstractC5544x8.a(this.F, R.drawable.f33530_resource_name_obfuscated_RES_2131231393));
            this.l0 = 0;
            c0(false);
            this.K = null;
            this.u0 = false;
        }
    }

    @Override // defpackage.W1
    public void e() {
        d0();
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        d0();
    }

    @Override // androidx.preference.Preference
    public void v() {
        super.v();
        this.x0.a(this);
        C5949zZ.a().d(Profile.b()).q(this);
        this.w0.U(this);
        XQ.c();
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.a(this);
        }
        d0();
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        AbstractC4656rv1.g(tf0.G, this.v0);
    }
}
