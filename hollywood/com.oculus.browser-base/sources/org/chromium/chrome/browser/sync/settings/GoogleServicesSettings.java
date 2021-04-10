package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.SignOutDialogFragment;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GoogleServicesSettings extends AbstractC2324eF0 implements XE0, AbstractC5942zV0 {
    public final PrefService G0 = Wr1.a(Profile.b());
    public final WF0 H0 = WF0.a();
    public final AbstractC1528Zb0 I0 = new C4071oW(this);
    public final PU0 J0 = NU0.f8549a;
    public ChromeSwitchPreference K0;
    public ChromeSwitchPreference L0;
    public ChromeSwitchPreference M0;
    public ChromeSwitchPreference N0;
    public ChromeSwitchPreference O0;
    public ChromeSwitchPreference P0;
    public ChromeSwitchPreference Q0;
    public ChromeSwitchPreference R0;
    public ChromeSwitchPreference S0;
    public Preference T0;
    public boolean U0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        l1();
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        String str = preference.Q;
        if ("allow_signin".equals(str)) {
            IdentityManager c = C5949zZ.a().c(Profile.b());
            if (!(c.b(0) != null && !((Boolean) obj).booleanValue())) {
                N.Mf2ABpoH(this.G0.f10883a, "signin.allowed", ((Boolean) obj).booleanValue());
                return true;
            }
            if (!(c.b(1) != null)) {
                C5949zZ.a().d(Profile.b()).h(3, null, false);
                N.Mf2ABpoH(this.G0.f10883a, "signin.allowed", false);
                return true;
            }
            SignOutDialogFragment l1 = SignOutDialogFragment.l1(0);
            l1.b1(this, 0);
            l1.k1(this.W, "sign_out_dialog_tag");
            return false;
        }
        if ("search_suggestions".equals(str)) {
            N.Mf2ABpoH(this.G0.f10883a, "search.suggest_enabled", ((Boolean) obj).booleanValue());
        } else if ("safe_browsing".equals(str)) {
            N.Mf2ABpoH(this.G0.f10883a, "safebrowsing.enabled", ((Boolean) obj).booleanValue());
            PostTask.b(Zo1.f9374a, new RunnableC3900nW(this), 0);
        } else if ("password_leak_detection".equals(str)) {
            N.Mf2ABpoH(this.G0.f10883a, "profile.password_manager_leak_detection", ((Boolean) obj).booleanValue());
        } else if ("safe_browsing_scout_reporting".equals(str)) {
            N.MjGeUNkF(((Boolean) obj).booleanValue());
        } else if ("navigation_error".equals(str)) {
            N.Mf2ABpoH(this.G0.f10883a, "alternate_error_pages.enabled", ((Boolean) obj).booleanValue());
        } else if ("usage_and_crash_reports".equals(str)) {
            UmaSessionStats.a(((Boolean) obj).booleanValue());
        } else if ("url_keyed_anonymized_data".equals(str)) {
            N.MnEYaN9w(Profile.b(), ((Boolean) obj).booleanValue());
        } else if ("autofill_assistant".equals(str)) {
            this.J0.m("autofill_assistant_switch", ((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // defpackage.AbstractC5942zV0
    public void c(boolean z) {
        if (AbstractC2531fV.m(C5949zZ.a(), 0) != null) {
            C5949zZ.a().d(Profile.b()).h(3, new C4242pW(this, new ClearDataProgressDialog()), z);
            N.Mf2ABpoH(this.G0.f10883a, "signin.allowed", false);
            l1();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0138, code lost:
        if (defpackage.AbstractC3983nz.f10523a.contains("autofill_assistant_switch") != false) goto L_0x013c;
     */
    @Override // defpackage.AbstractC2324eF0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void g1(android.os.Bundle r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 394
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.sync.settings.GoogleServicesSettings.g1(android.os.Bundle, java.lang.String):void");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(R.drawable.f30670_resource_name_obfuscated_RES_2131231107);
    }

    public final void k1() {
        boolean MzIXnlkD = N.MzIXnlkD(this.G0.f10883a, "safebrowsing.enabled");
        this.P0.K(MzIXnlkD);
        boolean z = true;
        this.P0.a0(MzIXnlkD && N.MWJZTkWR());
        boolean MnopluAe = N.MnopluAe();
        boolean MzIXnlkD2 = N.MzIXnlkD(this.G0.f10883a, "profile.password_manager_leak_detection");
        boolean z2 = MzIXnlkD && MnopluAe;
        this.O0.K(z2);
        ChromeSwitchPreference chromeSwitchPreference = this.O0;
        if (!z2 || !MzIXnlkD2) {
            z = false;
        }
        chromeSwitchPreference.a0(z);
        if (!MzIXnlkD || !MzIXnlkD2 || MnopluAe) {
            this.O0.T(null);
        } else {
            this.O0.S(R.string.f58120_resource_name_obfuscated_RES_2131953129);
        }
    }

    public final void l1() {
        this.K0.a0(N.MzIXnlkD(this.G0.f10883a, "signin.allowed"));
        this.L0.a0(N.MzIXnlkD(this.G0.f10883a, "search.suggest_enabled"));
        this.M0.a0(N.MzIXnlkD(this.G0.f10883a, "alternate_error_pages.enabled"));
        if (!this.U0) {
            this.N0.a0(N.MzIXnlkD(this.G0.f10883a, "safebrowsing.enabled"));
            k1();
        }
        this.Q0.a0(this.H0.b());
        this.R0.a0(N.Mfmn09fr(Profile.b()));
        ChromeSwitchPreference chromeSwitchPreference = this.S0;
        if (chromeSwitchPreference != null) {
            chromeSwitchPreference.a0(this.J0.d("autofill_assistant_switch", false));
        }
        if (this.T0 != null) {
            this.T0.S(ContextualSearchManager.j() ^ true ? R.string.f63330_resource_name_obfuscated_RES_2131953650 : R.string.f63320_resource_name_obfuscated_RES_2131953649);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        C2535fX.a().b(u(), O(R.string.f52650_resource_name_obfuscated_RES_2131952582), Profile.b(), null);
        return true;
    }
}
