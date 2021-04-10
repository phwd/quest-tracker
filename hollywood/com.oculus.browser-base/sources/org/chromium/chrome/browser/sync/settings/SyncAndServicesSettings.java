package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.TrustedVaultClient;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncAndServicesSettings extends AbstractC2324eF0 implements AbstractC3807mx0, XE0, AbstractC3526lH0 {
    public static final /* synthetic */ int G0 = 0;
    public final ProfileSyncService H0 = ProfileSyncService.b();
    public final PrefService I0 = Wr1.a(Profile.b());
    public final WF0 J0 = WF0.a();
    public final AbstractC1528Zb0 K0 = new U41(this);
    public final PU0 L0 = NU0.f8549a;
    public boolean M0;
    public SignInPreference N0;
    public Preference O0;
    public PreferenceCategory P0;
    public Preference Q0;
    public Preference R0;
    public ChromeBasePreference S0;
    public ChromeSwitchPreference T0;
    public ChromeSwitchPreference U0;
    public ChromeSwitchPreference V0;
    public ChromeSwitchPreference W0;
    public ChromeSwitchPreference X0;
    public ChromeSwitchPreference Y0;
    public ChromeSwitchPreference Z0;
    public ChromeSwitchPreference a1;
    public ChromeSwitchPreference b1;
    public Preference c1;
    public C3355kH0 d1;
    public int e1 = -1;
    public boolean f1;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CancelSyncDialog extends OE {
        @Override // defpackage.OE
        public Dialog g1(Bundle bundle) {
            C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
            e4Var.g(R.string.f48500_resource_name_obfuscated_RES_2131952167);
            e4Var.c(R.string.f48490_resource_name_obfuscated_RES_2131952166);
            e4Var.d(R.string.f47750_resource_name_obfuscated_RES_2131952092, new V41(this));
            e4Var.e(R.string.f48480_resource_name_obfuscated_RES_2131952165, new W41(this));
            return e4Var.a();
        }

        public final void l1() {
            AbstractC3535lK0.a("Signin_Signin_CancelCancelAdvancedSyncSettings");
            f1(false, false);
        }

        public final void m1() {
            AbstractC3535lK0.a("Signin_Signin_ConfirmCancelAdvancedSyncSettings");
            int i = SyncAndServicesSettings.G0;
            ((SyncAndServicesSettings) R()).m1();
        }
    }

    public static Bundle l1(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("SyncAndServicesPreferences.isFromSigninScreen", z);
        return bundle;
    }

    @Override // defpackage.AbstractC3807mx0
    public boolean A(String str) {
        if (!this.H0.h() || !this.H0.j() || str.isEmpty()) {
            return false;
        }
        ProfileSyncService profileSyncService = this.H0;
        if (!N.MlUAisy7(profileSyncService.e, profileSyncService, str)) {
            return false;
        }
        p1();
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        p1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void D0() {
        super.D0();
        this.H0.a(this);
        if (this.M0 && !C5949zZ.a().c(Profile.b()).c()) {
            this.M0 = false;
            this.k0.findViewById(R.id.bottom_bar_shadow).setVisibility(8);
            this.k0.findViewById(R.id.bottom_bar_button_container).setVisibility(8);
            Hl1 hl1 = ((Ty1) ((I7) u()).c0()).g;
            hl1.k = null;
            hl1.e();
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void E0() {
        super.E0();
        this.H0.q(this);
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        String str = preference.Q;
        if ("sync_requested".equals(str)) {
            AbstractC4175p51.a(((Boolean) obj).booleanValue());
            if (q1()) {
                ProfileSyncService profileSyncService = this.H0;
                N.MlP9oGhJ(profileSyncService.e, profileSyncService, 2);
            }
            PostTask.b(Zo1.f9374a, new R41(this), 0);
            return true;
        } else if ("search_suggestions".equals(str)) {
            PrefService prefService = this.I0;
            N.Mf2ABpoH(prefService.f10883a, "search.suggest_enabled", ((Boolean) obj).booleanValue());
            return true;
        } else if ("safe_browsing".equals(str)) {
            PrefService prefService2 = this.I0;
            N.Mf2ABpoH(prefService2.f10883a, "safebrowsing.enabled", ((Boolean) obj).booleanValue());
            PostTask.b(Zo1.f9374a, new S41(this), 0);
            return true;
        } else if ("password_leak_detection".equals(str)) {
            PrefService prefService3 = this.I0;
            N.Mf2ABpoH(prefService3.f10883a, "profile.password_manager_leak_detection", ((Boolean) obj).booleanValue());
            return true;
        } else if ("safe_browsing_scout_reporting".equals(str)) {
            N.MjGeUNkF(((Boolean) obj).booleanValue());
            return true;
        } else if ("navigation_error".equals(str)) {
            PrefService prefService4 = this.I0;
            N.Mf2ABpoH(prefService4.f10883a, "alternate_error_pages.enabled", ((Boolean) obj).booleanValue());
            return true;
        } else if ("usage_and_crash_reports".equals(str)) {
            UmaSessionStats.a(((Boolean) obj).booleanValue());
            return true;
        } else if ("url_keyed_anonymized_data".equals(str)) {
            N.MnEYaN9w(Profile.b(), ((Boolean) obj).booleanValue());
            return true;
        } else if (!"autofill_assistant".equals(str)) {
            return true;
        } else {
            this.L0.m("autofill_assistant_switch", ((Boolean) obj).booleanValue());
            return true;
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void c0(int i, int i2, Intent intent) {
        if (i == 1) {
            TrustedVaultClient.a().c();
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        boolean z = false;
        this.M0 = U20.c(this.K, "SyncAndServicesPreferences.isFromSigninScreen", false);
        u().setTitle(R.string.f59290_resource_name_obfuscated_RES_2131953246);
        V0(true);
        if (this.M0) {
            ((Ty1) ((I7) u()).c0()).g.c(R.string.f59300_resource_name_obfuscated_RES_2131953247);
            AbstractC3535lK0.a("Signin_Signin_ShowAdvancedSyncSettings");
        }
        AbstractC2870hT0.a(this, R.xml.f76430_resource_name_obfuscated_RES_2132213799);
        this.N0 = (SignInPreference) f1("sign_in");
        Preference f12 = f1("manage_your_google_account");
        this.O0 = f12;
        f12.K = new C3833n51(this, new N41(this));
        this.P0 = (PreferenceCategory) f1("sync_category");
        Preference f13 = f1("sync_error_card");
        this.Q0 = f13;
        Drawable f = AbstractC2417ep1.f(u(), R.drawable.f32780_resource_name_obfuscated_RES_2131231318, R.color.f11410_resource_name_obfuscated_RES_2131099831);
        if (f13.P != f) {
            f13.P = f;
            f13.O = 0;
            f13.s();
        }
        this.Q0.K = new C3833n51(this, new O41(this));
        Preference f14 = f1("sync_disabled_by_administrator");
        this.R0 = f14;
        f14.M(R.drawable.f29660_resource_name_obfuscated_RES_2131231006);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("sync_requested");
        this.T0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = this;
        this.S0 = (ChromeBasePreference) f1("manage_sync");
        ChromeSwitchPreference chromeSwitchPreference2 = (ChromeSwitchPreference) f1("search_suggestions");
        this.U0 = chromeSwitchPreference2;
        chromeSwitchPreference2.f9480J = this;
        AbstractC1528Zb0 zb0 = this.K0;
        chromeSwitchPreference2.B0 = zb0;
        AbstractC1865bc0.b(zb0, chromeSwitchPreference2);
        ChromeSwitchPreference chromeSwitchPreference3 = (ChromeSwitchPreference) f1("navigation_error");
        this.V0 = chromeSwitchPreference3;
        chromeSwitchPreference3.f9480J = this;
        AbstractC1528Zb0 zb02 = this.K0;
        chromeSwitchPreference3.B0 = zb02;
        AbstractC1865bc0.b(zb02, chromeSwitchPreference3);
        PreferenceCategory preferenceCategory = (PreferenceCategory) f1("services_category");
        boolean M09VlOh_ = N.M09VlOh_("SafeBrowsingSecuritySectionUIAndroid");
        this.f1 = M09VlOh_;
        if (M09VlOh_) {
            preferenceCategory.g0(f1("safe_browsing"));
            preferenceCategory.u();
            preferenceCategory.g0(f1("password_leak_detection"));
            preferenceCategory.u();
            preferenceCategory.g0(f1("safe_browsing_scout_reporting"));
            preferenceCategory.u();
            this.W0 = null;
            this.X0 = null;
            this.Y0 = null;
        } else {
            ChromeSwitchPreference chromeSwitchPreference4 = (ChromeSwitchPreference) f1("safe_browsing");
            this.W0 = chromeSwitchPreference4;
            chromeSwitchPreference4.f9480J = this;
            AbstractC1528Zb0 zb03 = this.K0;
            chromeSwitchPreference4.B0 = zb03;
            AbstractC1865bc0.b(zb03, chromeSwitchPreference4);
            ChromeSwitchPreference chromeSwitchPreference5 = (ChromeSwitchPreference) f1("password_leak_detection");
            this.X0 = chromeSwitchPreference5;
            chromeSwitchPreference5.f9480J = this;
            AbstractC1528Zb0 zb04 = this.K0;
            chromeSwitchPreference5.B0 = zb04;
            AbstractC1865bc0.b(zb04, chromeSwitchPreference5);
            ChromeSwitchPreference chromeSwitchPreference6 = (ChromeSwitchPreference) f1("safe_browsing_scout_reporting");
            this.Y0 = chromeSwitchPreference6;
            chromeSwitchPreference6.f9480J = this;
            AbstractC1528Zb0 zb05 = this.K0;
            chromeSwitchPreference6.B0 = zb05;
            AbstractC1865bc0.b(zb05, chromeSwitchPreference6);
        }
        if (!N.M09VlOh_("MetricsSettingsAndroid")) {
            preferenceCategory.g0(f1("metrics_settings"));
            preferenceCategory.u();
        }
        ChromeSwitchPreference chromeSwitchPreference7 = (ChromeSwitchPreference) f1("usage_and_crash_reports");
        this.Z0 = chromeSwitchPreference7;
        chromeSwitchPreference7.f9480J = this;
        AbstractC1528Zb0 zb06 = this.K0;
        chromeSwitchPreference7.B0 = zb06;
        AbstractC1865bc0.b(zb06, chromeSwitchPreference7);
        ChromeSwitchPreference chromeSwitchPreference8 = (ChromeSwitchPreference) f1("url_keyed_anonymized_data");
        this.a1 = chromeSwitchPreference8;
        chromeSwitchPreference8.f9480J = this;
        AbstractC1528Zb0 zb07 = this.K0;
        chromeSwitchPreference8.B0 = zb07;
        AbstractC1865bc0.b(zb07, chromeSwitchPreference8);
        this.b1 = (ChromeSwitchPreference) f1("autofill_assistant");
        Preference f15 = f1("autofill_assistant_subsection");
        if (N.M09VlOh_("AutofillAssistantProactiveHelp") || N.M09VlOh_("OmniboxAssistantVoiceSearch")) {
            preferenceCategory.g0(this.b1);
            preferenceCategory.u();
            this.b1 = null;
            f15.W(true);
        } else {
            if (N.M09VlOh_("AutofillAssistant")) {
                this.L0.f8694a.a("autofill_assistant_switch");
                if (AbstractC3983nz.f10523a.contains("autofill_assistant_switch")) {
                    z = true;
                }
            }
            if (z) {
                ChromeSwitchPreference chromeSwitchPreference9 = this.b1;
                chromeSwitchPreference9.f9480J = this;
                AbstractC1528Zb0 zb08 = this.K0;
                chromeSwitchPreference9.B0 = zb08;
                AbstractC1865bc0.b(zb08, chromeSwitchPreference9);
            } else {
                preferenceCategory.g0(this.b1);
                preferenceCategory.u();
                this.b1 = null;
            }
        }
        this.c1 = f1("contextual_search");
        if (!AbstractC5686xz.e()) {
            preferenceCategory.g0(this.c1);
            preferenceCategory.u();
            this.c1 = null;
        }
        this.d1 = this.H0.f();
        p1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(R.drawable.f30670_resource_name_obfuscated_RES_2131231107);
    }

    /* renamed from: k1 */
    public final void m1() {
        AbstractC3535lK0.a("Signin_Signin_CancelAdvancedSyncSettings");
        C5949zZ.a().d(Profile.b()).u(3);
        u().finish();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) super.l0(layoutInflater, viewGroup, bundle);
        if (this.M0) {
            layoutInflater.inflate(R.layout.f41660_resource_name_obfuscated_RES_2131624475, viewGroup2, true);
            ((ButtonCompat) viewGroup2.findViewById(R.id.cancel_button)).setOnClickListener(new P41(this));
            ((ButtonCompat) viewGroup2.findViewById(R.id.confirm_button)).setOnClickListener(new Q41(this));
        }
        return viewGroup2;
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        p1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        if (q1() && this.N0.y0 == 3) {
            AbstractC4175p51.a(false);
            ProfileSyncService profileSyncService = this.H0;
            N.MlP9oGhJ(profileSyncService.e, profileSyncService, 3);
        }
        this.d1.a();
    }

    @Override // defpackage.AbstractC3807mx0
    public void n() {
    }

    public final void n1() {
        AbstractC3535lK0.a("Signin_Signin_ConfirmAdvancedSyncSettings");
        ProfileSyncService b = ProfileSyncService.b();
        N.MlP9oGhJ(b.e, b, 1);
        N.M2AYruv7(Profile.b());
        u().finish();
    }

    public final void o1() {
        boolean MzIXnlkD = N.MzIXnlkD(this.I0.f10883a, "safebrowsing.enabled");
        this.Y0.K(MzIXnlkD);
        boolean z = true;
        this.Y0.a0(MzIXnlkD && N.MWJZTkWR());
        boolean MnopluAe = N.MnopluAe();
        boolean MzIXnlkD2 = N.MzIXnlkD(this.I0.f10883a, "profile.password_manager_leak_detection");
        boolean z2 = MzIXnlkD && MnopluAe;
        this.X0.K(z2);
        ChromeSwitchPreference chromeSwitchPreference = this.X0;
        if (!z2 || !MzIXnlkD2) {
            z = false;
        }
        chromeSwitchPreference.a0(z);
        if (!MzIXnlkD || !MzIXnlkD2 || MnopluAe) {
            this.X0.T(null);
        } else {
            this.X0.S(R.string.f58120_resource_name_obfuscated_RES_2131953129);
        }
    }

    public final void p1() {
        String str;
        KS ks;
        OE oe;
        if (!((this.H0.h() && this.H0.j()) || (ks = this.W) == null || (oe = (OE) ks.J("enter_password")) == null)) {
            oe.f1(false, false);
        }
        if (!C5949zZ.a().c(Profile.b()).c()) {
            this.z0.g.f0(this.O0);
            this.z0.g.f0(this.P0);
        } else {
            this.z0.g.a0(this.O0);
            this.z0.g.a0(this.P0);
            if (ProfileSyncService.b().l()) {
                this.P0.a0(this.R0);
                this.P0.f0(this.Q0);
                this.P0.f0(this.T0);
                this.P0.f0(this.S0);
            } else {
                this.P0.f0(this.R0);
                this.P0.a0(this.T0);
                this.P0.a0(this.S0);
                int c = AbstractC4175p51.c();
                if (c == 6 && this.M0) {
                    c = -1;
                }
                this.e1 = c;
                if (c == -1) {
                    this.P0.f0(this.Q0);
                } else {
                    Preference preference = this.Q0;
                    if (c == 3) {
                        str = O(R.string.f62680_resource_name_obfuscated_RES_2131953585);
                    } else if (c == 4) {
                        str = O(R.string.f62970_resource_name_obfuscated_RES_2131953614);
                    } else if (c != 6) {
                        str = O(R.string.f62680_resource_name_obfuscated_RES_2131953585);
                    } else {
                        str = O(R.string.f63040_resource_name_obfuscated_RES_2131953621);
                    }
                    preference.V(str);
                    this.Q0.T(AbstractC4175p51.d(u(), this.e1));
                    this.P0.a0(this.Q0);
                }
                ChromeSwitchPreference chromeSwitchPreference = this.T0;
                C4858t6 c2 = C4858t6.c();
                Objects.requireNonNull(c2);
                Object obj = ThreadUtils.f10596a;
                chromeSwitchPreference.a0(c2.f);
                if (q1()) {
                    this.T0.a0(false);
                }
                this.T0.K(!Profile.b().f());
            }
        }
        this.U0.a0(N.MzIXnlkD(this.I0.f10883a, "search.suggest_enabled"));
        this.V0.a0(N.MzIXnlkD(this.I0.f10883a, "alternate_error_pages.enabled"));
        if (!this.f1) {
            this.W0.a0(N.MzIXnlkD(this.I0.f10883a, "safebrowsing.enabled"));
            o1();
        }
        this.Z0.a0(this.J0.b());
        this.a1.a0(N.Mfmn09fr(Profile.b()));
        ChromeSwitchPreference chromeSwitchPreference2 = this.b1;
        if (chromeSwitchPreference2 != null) {
            chromeSwitchPreference2.a0(this.L0.d("autofill_assistant_switch", false));
        }
        if (this.c1 != null) {
            this.c1.S(ContextualSearchManager.j() ^ true ? R.string.f63330_resource_name_obfuscated_RES_2131953650 : R.string.f63320_resource_name_obfuscated_RES_2131953649);
        }
    }

    public final boolean q1() {
        return !this.M0 && !this.H0.i();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            if (!this.M0) {
                return false;
            }
            AbstractC3535lK0.a("Signin_Signin_BackOnAdvancedSyncSettings");
            CancelSyncDialog cancelSyncDialog = new CancelSyncDialog();
            cancelSyncDialog.b1(this, 0);
            cancelSyncDialog.k1(this.W, "cancel_sync_dialog");
            return true;
        } else if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        } else {
            C2535fX.a().b(u(), O(R.string.f52650_resource_name_obfuscated_RES_2131952582), Profile.b(), null);
            return true;
        }
    }
}
