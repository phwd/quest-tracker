package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.oculus.browser.R;
import java.util.HashSet;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.TrustedVaultClient;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ManageSyncSettings extends AbstractC2324eF0 implements AbstractC3807mx0, AbstractC2611fx0, AbstractC4320px0, XE0, AbstractC3526lH0, AbstractC5942zV0, AbstractC1783b51 {
    public static final /* synthetic */ int G0 = 0;
    public final ProfileSyncService H0 = ProfileSyncService.b();
    public boolean I0;
    public SyncErrorCardPreference J0;
    public PreferenceCategory K0;
    public ChromeSwitchPreference L0;
    public ChromeBaseCheckBoxPreference M0;
    public ChromeBaseCheckBoxPreference N0;
    public ChromeBaseCheckBoxPreference O0;
    public ChromeBaseCheckBoxPreference P0;
    public ChromeBaseCheckBoxPreference Q0;
    public ChromeBaseCheckBoxPreference R0;
    public ChromeBaseCheckBoxPreference S0;
    public ChromeBaseCheckBoxPreference[] T0;
    public Preference U0;
    public Preference V0;
    public Preference W0;
    public Preference X0;
    public PreferenceCategory Y0;
    public ChromeSwitchPreference Z0;
    public C3355kH0 a1;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CancelSyncDialog extends OE {
        @Override // defpackage.OE
        public Dialog g1(Bundle bundle) {
            C2290e4 e4Var = new C2290e4(u(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
            e4Var.g(R.string.f48500_resource_name_obfuscated_RES_2131952167);
            e4Var.c(R.string.f48490_resource_name_obfuscated_RES_2131952166);
            e4Var.d(R.string.f47750_resource_name_obfuscated_RES_2131952092, new DialogInterface$OnClickListenerC1223Ub0(this));
            e4Var.e(R.string.f48480_resource_name_obfuscated_RES_2131952165, new DialogInterface$OnClickListenerC1284Vb0(this));
            return e4Var.a();
        }

        public final void l1() {
            AbstractC3535lK0.a("Signin_Signin_CancelCancelAdvancedSyncSettings");
            f1(false, false);
        }

        public final void m1() {
            AbstractC3535lK0.a("Signin_Signin_ConfirmCancelAdvancedSyncSettings");
            int i = ManageSyncSettings.G0;
            ((ManageSyncSettings) R()).n1();
        }
    }

    public static Bundle m1(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("ManageSyncSettings.isFromSigninScreen", z);
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
        l1("enter_password");
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
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void E0() {
        super.E0();
        this.H0.q(this);
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        PostTask.b(Zo1.f9374a, new RunnableC0979Qb0(this), 0);
        return true;
    }

    @Override // defpackage.AbstractC5942zV0
    public void c(boolean z) {
        Profile b = Profile.b();
        if (C5949zZ.a().c(b).c()) {
            C5949zZ.a().d(b).h(3, new C1162Tb0(this, new ClearDataProgressDialog()), z);
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
        this.I0 = U20.c(this.K, "ManageSyncSettings.isFromSigninScreen", false);
        u().setTitle(N.M09VlOh_("MobileIdentityConsistency") ? R.string.f62620_resource_name_obfuscated_RES_2131953579 : R.string.f54320_resource_name_obfuscated_RES_2131952749);
        V0(true);
        AbstractC2870hT0.a(this, R.xml.f76280_resource_name_obfuscated_RES_2132213784);
        SyncErrorCardPreference syncErrorCardPreference = (SyncErrorCardPreference) f1("sync_error_card");
        this.J0 = syncErrorCardPreference;
        syncErrorCardPreference.u0 = this;
        this.K0 = (PreferenceCategory) f1("syncing_category");
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("sync_everything");
        this.L0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = this;
        this.M0 = (ChromeBaseCheckBoxPreference) f1("sync_autofill");
        this.N0 = (ChromeBaseCheckBoxPreference) f1("sync_bookmarks");
        this.O0 = (ChromeBaseCheckBoxPreference) f1("sync_payments_integration");
        this.P0 = (ChromeBaseCheckBoxPreference) f1("sync_history");
        this.Q0 = (ChromeBaseCheckBoxPreference) f1("sync_passwords");
        this.R0 = (ChromeBaseCheckBoxPreference) f1("sync_recent_tabs");
        this.S0 = (ChromeBaseCheckBoxPreference) f1("sync_settings");
        Preference f1 = f1("turn_off_sync");
        this.U0 = f1;
        f1.K = new C3833n51(this, new RunnableC0492Ib0(this));
        Profile b = Profile.b();
        if (N.M09VlOh_("MobileIdentityConsistency") && !this.I0) {
            this.U0.W(!b.f());
            f1("advanced_category").W(true);
            if (!ProfileSyncService.b().m()) {
                ProfileSyncService.b().r(false, new HashSet());
            }
        }
        this.V0 = f1("google_activity_controls");
        Preference f12 = f1("encryption");
        this.W0 = f12;
        f12.K = new C3833n51(this, new RunnableC0614Kb0(this));
        Preference f13 = f1("sync_manage_data");
        this.X0 = f13;
        f13.K = new C3833n51(this, new RunnableC0675Lb0(this));
        ChromeBaseCheckBoxPreference[] chromeBaseCheckBoxPreferenceArr = {this.M0, this.N0, this.O0, this.P0, this.Q0, this.R0, this.S0};
        this.T0 = chromeBaseCheckBoxPreferenceArr;
        for (ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference : chromeBaseCheckBoxPreferenceArr) {
            chromeBaseCheckBoxPreference.f9480J = this;
        }
        if (b.f()) {
            this.V0.S(R.string.f61860_resource_name_obfuscated_RES_2131953503);
        }
        this.a1 = this.H0.f();
        this.Y0 = (PreferenceCategory) f1("search_and_browse_category");
        ChromeSwitchPreference chromeSwitchPreference2 = (ChromeSwitchPreference) f1("url_keyed_anonymized_data");
        this.Z0 = chromeSwitchPreference2;
        chromeSwitchPreference2.a0(N.Mfmn09fr(b));
        ChromeSwitchPreference chromeSwitchPreference3 = this.Z0;
        chromeSwitchPreference3.f9480J = new C0735Mb0(b);
        C0796Nb0 nb0 = new C0796Nb0(b);
        chromeSwitchPreference3.B0 = nb0;
        AbstractC1865bc0.b(nb0, chromeSwitchPreference3);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(R.drawable.f30670_resource_name_obfuscated_RES_2131231107);
    }

    /* renamed from: k1 */
    public final void n1() {
        AbstractC3535lK0.a("Signin_Signin_CancelAdvancedSyncSettings");
        C5949zZ.a().d(Profile.b()).u(3);
        u().finish();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!N.M09VlOh_("MobileIdentityConsistency") || !this.I0) {
            return super.l0(layoutInflater, viewGroup, bundle);
        }
        ViewGroup viewGroup2 = (ViewGroup) super.l0(layoutInflater, viewGroup, bundle);
        layoutInflater.inflate(R.layout.f41660_resource_name_obfuscated_RES_2131624475, viewGroup2, true);
        ((ButtonCompat) viewGroup2.findViewById(R.id.cancel_button)).setOnClickListener(new View$OnClickListenerC0857Ob0(this));
        ((ButtonCompat) viewGroup2.findViewById(R.id.confirm_button)).setOnClickListener(new View$OnClickListenerC0918Pb0(this));
        this.Y0.W(true);
        this.K0.W(true);
        return viewGroup2;
    }

    public final void l1(String str) {
        OE oe;
        KS ks = this.W;
        if (ks != null && (oe = (OE) ks.J(str)) != null) {
            oe.f1(false, false);
        }
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        PostTask.b(Zo1.f9374a, new RunnableC1040Rb0(this), 0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        this.i0 = true;
        this.a1.a();
    }

    @Override // defpackage.AbstractC3807mx0
    public void n() {
    }

    public final void o1() {
        AbstractC3535lK0.a("Signin_Signin_ConfirmAdvancedSyncSettings");
        ProfileSyncService b = ProfileSyncService.b();
        N.MlP9oGhJ(b.e, b, 1);
        N.M2AYruv7(Profile.b());
        u().finish();
    }

    public final void p1() {
        boolean z = true;
        String b = CoreAccountInfo.b(C5949zZ.a().c(Profile.b()).b(1));
        if (b == null) {
            u().finish();
            return;
        }
        this.V0.K = new C3833n51(this, new RunnableC1101Sb0(this, b));
        ProfileSyncService profileSyncService = this.H0;
        boolean MpBx$QMz = N.MpBx$QMz(profileSyncService.e, profileSyncService);
        this.L0.a0(MpBx$QMz);
        if (MpBx$QMz) {
            ChromeBaseCheckBoxPreference[] chromeBaseCheckBoxPreferenceArr = this.T0;
            for (ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference : chromeBaseCheckBoxPreferenceArr) {
                chromeBaseCheckBoxPreference.a0(true);
                chromeBaseCheckBoxPreference.K(false);
            }
        } else {
            ProfileSyncService profileSyncService2 = this.H0;
            HashSet hashSet = (HashSet) ProfileSyncService.p(N.M_gH1Vgj(profileSyncService2.e, profileSyncService2));
            this.M0.a0(hashSet.contains(6));
            this.M0.K(true);
            this.N0.a0(hashSet.contains(2));
            this.N0.K(true);
            this.P0.a0(hashSet.contains(11));
            this.P0.K(true);
            this.Q0.a0(hashSet.contains(4));
            this.Q0.K(true);
            this.R0.a0(hashSet.contains(39));
            this.R0.K(true);
            this.S0.a0(hashSet.contains(3));
            this.S0.K(true);
            boolean contains = hashSet.contains(6);
            ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference2 = this.O0;
            if (!contains || !N.M4NdKhmj()) {
                z = false;
            }
            chromeBaseCheckBoxPreference2.a0(z);
            this.O0.K(contains);
        }
        boolean h = this.H0.h();
        this.W0.K(h);
        this.W0.T(null);
        if (!h) {
            l1("custom_password");
            l1("enter_password");
            return;
        }
        ProfileSyncService profileSyncService3 = this.H0;
        if (N.M8uQ8DWG(profileSyncService3.e, profileSyncService3)) {
            l1("custom_password");
            l1("enter_password");
            this.W0.S(this.H0.g() ? R.string.f62680_resource_name_obfuscated_RES_2131953585 : R.string.f62970_resource_name_obfuscated_RES_2131953614);
            return;
        }
        if (!this.H0.j()) {
            l1("enter_password");
        }
        if (this.H0.j() && U()) {
            Preference preference = this.W0;
            String O = O(R.string.f62850_resource_name_obfuscated_RES_2131953602);
            Activity u = u();
            SpannableString spannableString = new SpannableString(O);
            spannableString.setSpan(new ForegroundColorSpan(u.getResources().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976)), 0, spannableString.length(), 0);
            preference.T(spannableString);
        }
    }

    public final void q1() {
        HashSet hashSet = new HashSet();
        if (this.M0.t0) {
            hashSet.add(6);
        }
        if (this.N0.t0) {
            hashSet.add(2);
        }
        if (this.P0.t0) {
            hashSet.add(11);
        }
        if (this.Q0.t0) {
            hashSet.add(4);
        }
        if (this.R0.t0) {
            hashSet.add(39);
        }
        if (this.S0.t0) {
            hashSet.add(3);
        }
        this.H0.r(this.L0.t0, hashSet);
        N.MIN2Dr59(this.L0.t0 || (this.O0.t0 && this.M0.t0));
        if (N.M09VlOh_("MobileIdentityConsistency") && !Profile.b().f()) {
            boolean z = this.L0.t0 || hashSet.size() > 0;
            if (this.H0.m() && !z) {
                ProfileSyncService profileSyncService = this.H0;
                N.MmphYbNU(profileSyncService.e, profileSyncService, false);
            } else if (!this.H0.m() && z) {
                ProfileSyncService profileSyncService2 = this.H0;
                N.MmphYbNU(profileSyncService2.e, profileSyncService2, true);
            }
        }
        PostTask.b(Zo1.f9374a, new RunnableC0553Jb0(this), 0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (!N.M09VlOh_("MobileIdentityConsistency") || menuItem.getItemId() != 16908332) {
            if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
                return false;
            }
            C2535fX.a().b(u(), O(R.string.f52650_resource_name_obfuscated_RES_2131952582), Profile.b(), null);
            return true;
        } else if (!this.I0) {
            return false;
        } else {
            AbstractC3535lK0.a("Signin_Signin_BackOnAdvancedSyncSettings");
            CancelSyncDialog cancelSyncDialog = new CancelSyncDialog();
            cancelSyncDialog.b1(this, 0);
            cancelSyncDialog.k1(this.W, "cancel_sync_dialog");
            return true;
        }
    }
}
