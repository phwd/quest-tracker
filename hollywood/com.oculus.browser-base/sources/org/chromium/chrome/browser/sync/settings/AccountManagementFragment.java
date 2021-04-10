package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.accounts.Account;
import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.SignOutDialogFragment;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountManagementFragment extends AbstractC2324eF0 implements AbstractC5942zV0, AbstractC2534fW0, VG0 {
    public static final /* synthetic */ int G0 = 0;
    public int H0 = 0;
    public Profile I0;
    public String J0;
    public WG0 K0;
    public C3355kH0 L0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        C5949zZ.a().d(Profile.b()).m(this);
        this.K0.U(this);
        this.K0.Z(V1.d(AccountManagerFacadeProvider.getInstance().n()));
        o1();
    }

    @Override // defpackage.VG0
    public void D(String str) {
        p1();
    }

    @Override // defpackage.AbstractC2534fW0
    public void b() {
        o1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        i1(null);
        this.A0.s0(null);
    }

    @Override // defpackage.AbstractC5942zV0
    public void c(boolean z) {
        if (AbstractC2531fV.m(C5949zZ.a(), 0) != null) {
            C5949zZ.a().d(Profile.b()).h(3, new O0(this, new ClearDataProgressDialog()), z);
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            this.L0 = b.f();
        }
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.H0 = bundle2.getInt("ShowGAIAServiceType", this.H0);
        }
        this.I0 = Profile.b();
        int i = 0;
        N.MX17n_KK(0, this.H0);
        Activity u = u();
        if (this.I0.f()) {
            i = R.drawable.f29460_resource_name_obfuscated_RES_2131230986;
        }
        this.K0 = WG0.V(u, i);
    }

    public final Preference k1(Account account) {
        Preference preference = new Preference(this.z0.f11127a, null);
        preference.k0 = R.layout.f36620_resource_name_obfuscated_RES_2131623971;
        preference.V(account.name);
        preference.N(this.K0.W(account.name).b);
        preference.K = new C3833n51(this, new J0(this, account));
        return preference;
    }

    public final boolean l1() {
        if (!a0() || !Z() || this.J0 == null) {
            return false;
        }
        N.MX17n_KK(5, this.H0);
        if (AbstractC2531fV.m(C5949zZ.a(), 1) != null) {
            SignOutDialogFragment l1 = SignOutDialogFragment.l1(this.H0);
            l1.b1(this, 0);
            l1.k1(this.W, "sign_out_dialog_tag");
        } else {
            C5949zZ.a().d(Profile.b()).h(3, null, false);
        }
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        C3355kH0 kh0 = this.L0;
        if (kh0 != null) {
            kh0.a();
        }
    }

    public final boolean m1() {
        if (!a0() || !Z()) {
            return false;
        }
        N.MX17n_KK(1, this.H0);
        AccountManagerFacadeProvider.getInstance().q(new N0(this));
        return true;
    }

    public final boolean n1() {
        return !(!((UserManager) u().getSystemService("user")).hasUserRestriction("no_modify_accounts"));
    }

    @Override // defpackage.AbstractC2534fW0
    public void o() {
        o1();
    }

    public void o1() {
        CharSequence charSequence;
        int i;
        if (u() != null) {
            PreferenceScreen preferenceScreen = this.z0.g;
            if (preferenceScreen != null) {
                preferenceScreen.e0();
            }
            String b = CoreAccountInfo.b(C5949zZ.a().c(Profile.b()).b(!N.M09VlOh_("MobileIdentityConsistency") ? 1 : 0));
            this.J0 = b;
            if (b == null) {
                u().finish();
                return;
            }
            e1(R.xml.f76060_resource_name_obfuscated_RES_2132213762);
            u().setTitle(this.K0.W(this.J0).a());
            Preference f1 = f1("sign_out");
            if (this.I0.f()) {
                this.z0.g.f0(f1);
                this.z0.g.f0(f1("sign_out_divider"));
            } else {
                if (N.M09VlOh_("MobileIdentityConsistency")) {
                    f1.k0 = R.layout.f36620_resource_name_obfuscated_RES_2131623971;
                    f1.M(R.drawable.f32690_resource_name_obfuscated_RES_2131231309);
                }
                f1.U((!N.M09VlOh_("MobileIdentityConsistency") || C5949zZ.a().c(Profile.b()).c()) ? R.string.f61960_resource_name_obfuscated_RES_2131953513 : R.string.f61950_resource_name_obfuscated_RES_2131953512);
                f1.K = new I0(this);
            }
            Preference f12 = f1("parent_accounts");
            Preference f13 = f1("child_content");
            if (this.I0.f()) {
                PrefService a2 = Wr1.a(this.I0);
                String Ma80fvz5 = N.Ma80fvz5(a2.f10883a, "profile.managed.custodian_email");
                String Ma80fvz52 = N.Ma80fvz5(a2.f10883a, "profile.managed.second_custodian_email");
                if (!Ma80fvz52.isEmpty()) {
                    charSequence = P(R.string.f46490_resource_name_obfuscated_RES_2131951966, Ma80fvz5, Ma80fvz52);
                } else if (!Ma80fvz5.isEmpty()) {
                    charSequence = P(R.string.f46450_resource_name_obfuscated_RES_2131951962, Ma80fvz5);
                } else {
                    charSequence = O(R.string.f46440_resource_name_obfuscated_RES_2131951961);
                }
                f12.T(charSequence);
                if (N.MzGf81GW(a2.f10883a, "profile.managed.default_filtering_behavior") == 2) {
                    i = R.string.f46410_resource_name_obfuscated_RES_2131951958;
                } else {
                    i = N.MzIXnlkD(a2.f10883a, "profile.managed.safe_sites") ? R.string.f46420_resource_name_obfuscated_RES_2131951959 : R.string.f46400_resource_name_obfuscated_RES_2131951957;
                }
                f13.S(i);
                Drawable c = AbstractC3153j7.c(I(), R.drawable.f30000_resource_name_obfuscated_RES_2131231040);
                c.mutate().setColorFilter(I().getColor(R.color.f11220_resource_name_obfuscated_RES_2131099812), PorterDuff.Mode.SRC_IN);
                if (f13.P != c) {
                    f13.P = c;
                    f13.O = 0;
                    f13.s();
                }
            } else {
                PreferenceScreen preferenceScreen2 = this.z0.g;
                preferenceScreen2.f0(f1("parental_settings"));
                preferenceScreen2.f0(f12);
                preferenceScreen2.f0(f13);
                preferenceScreen2.f0(f1("child_content_divider"));
            }
            p1();
        }
    }

    public final void p1() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) f1("accounts_category");
        if (preferenceCategory != null) {
            preferenceCategory.e0();
            preferenceCategory.a0(k1(V1.b(this.J0)));
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                Preference preference = new Preference(this.z0.f11127a, null);
                preference.k0 = R.layout.f36610_resource_name_obfuscated_RES_2131623970;
                preferenceCategory.a0(preference);
                Preference preference2 = new Preference(this.z0.f11127a, null);
                preference2.k0 = R.layout.f36620_resource_name_obfuscated_RES_2131623971;
                preference2.U(R.string.f54330_resource_name_obfuscated_RES_2131952750);
                preference2.M(R.drawable.f30330_resource_name_obfuscated_RES_2131231073);
                preference2.K = new C3833n51(this, new K0(this));
                preferenceCategory.a0(preference2);
                Preference preference3 = new Preference(this.z0.f11127a, null);
                preference3.k0 = R.layout.f38020_resource_name_obfuscated_RES_2131624111;
                preferenceCategory.a0(preference3);
            }
            for (Account account : AccountManagerFacadeProvider.getInstance().n()) {
                if (!this.J0.equals(account.name)) {
                    preferenceCategory.a0(k1(account));
                }
            }
            if (!this.I0.f()) {
                ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.z0.f11127a);
                chromeBasePreference.k0 = R.layout.f36620_resource_name_obfuscated_RES_2131623971;
                if (N.M09VlOh_("MobileIdentityConsistency")) {
                    chromeBasePreference.M(R.drawable.f32430_resource_name_obfuscated_RES_2131231283);
                    chromeBasePreference.U(R.string.f62070_resource_name_obfuscated_RES_2131953524);
                } else {
                    chromeBasePreference.M(R.drawable.f29510_resource_name_obfuscated_RES_2131230991);
                    chromeBasePreference.U(R.string.f46390_resource_name_obfuscated_RES_2131951956);
                }
                chromeBasePreference.K = new L0(this);
                M0 m0 = new M0(this);
                chromeBasePreference.u0 = m0;
                AbstractC1865bc0.b(m0, chromeBasePreference);
                preferenceCategory.a0(chromeBasePreference);
            }
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void w0() {
        this.i0 = true;
        C5949zZ.a().d(Profile.b()).z(this);
        this.K0.Y(this);
    }
}
