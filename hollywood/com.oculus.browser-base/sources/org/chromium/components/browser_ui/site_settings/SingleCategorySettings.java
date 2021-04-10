package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.settings.ExpandablePreferenceGroup;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SingleCategorySettings extends SiteSettingsPreferenceFragment implements XE0, YE0, AbstractC3996o3 {
    public RecyclerView H0;
    public MenuItem I0;
    public QX0 J0;
    public String K0;
    public boolean L0;
    public boolean M0;
    public boolean N0 = true;
    public boolean O0;
    public boolean P0 = true;
    public int Q0;
    public boolean R0;
    public boolean S0;
    public ChromeBaseCheckBoxPreference T0;
    public Set U0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        MenuItem menuItem;
        this.i0 = true;
        if (this.K0 == null && (menuItem = this.I0) != null) {
            RQ0.a(menuItem, u());
            this.K0 = null;
        }
        n1();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0085  */
    @Override // defpackage.XE0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(androidx.preference.Preference r7, java.lang.Object r8) {
        /*
        // Method dump skipped, instructions count: 220
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.SingleCategorySettings.a(androidx.preference.Preference, java.lang.Object):boolean");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        AbstractC2870hT0.a(this, R.xml.f76460_resource_name_obfuscated_RES_2132213802);
        String string = this.K.getString("title");
        if (string != null) {
            u().setTitle(string);
        }
        this.U0 = this.K.containsKey("selected_domains") ? new HashSet(this.K.getStringArrayList("selected_domains")) : null;
        k1();
        V0(true);
        this.i0 = true;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        if ("allowed_group".equals(preference.Q)) {
            this.N0 = !this.N0;
        } else if ("blocked_group".equals(preference.Q)) {
            this.M0 = !this.M0;
        } else {
            this.O0 = !this.O0;
        }
        n1();
        return true;
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.f42510_resource_name_obfuscated_RES_2131689485, menu);
        MenuItem findItem = menu.findItem(R.id.search);
        this.I0 = findItem;
        RQ0.d(findItem, this.K0, u(), new XW0(this));
        Objects.requireNonNull(this.G0);
        menu.add(0, R.id.menu_id_site_settings_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, x().getTheme()));
    }

    public final void k1() {
        boolean z;
        int[] iArr;
        int i = this.J0.i();
        PreferenceScreen preferenceScreen = this.z0.g;
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) preferenceScreen.b0("binary_toggle");
        TriStateSiteSettingsPreference triStateSiteSettingsPreference = (TriStateSiteSettingsPreference) preferenceScreen.b0("tri_state_toggle");
        FourStateCookieSettingsPreference fourStateCookieSettingsPreference = (FourStateCookieSettingsPreference) preferenceScreen.b0("four_state_cookie_toggle");
        Preference b0 = preferenceScreen.b0("notifications_vibrate");
        Preference b02 = preferenceScreen.b0("notifications_quiet_ui");
        Preference b03 = preferenceScreen.b0("protected_content_learn_more");
        AbstractC2837hF0 hf0 = (AbstractC2837hF0) preferenceScreen.b0("allowed_group");
        AbstractC2837hF0 hf02 = (AbstractC2837hF0) preferenceScreen.b0("blocked_group");
        AbstractC2837hF0 hf03 = (AbstractC2837hF0) preferenceScreen.b0("managed_group");
        boolean q = this.J0.q(x());
        if (this.R0) {
            preferenceScreen.f0(chromeSwitchPreference);
            preferenceScreen.f0(fourStateCookieSettingsPreference);
            triStateSiteSettingsPreference.f9480J = this;
            int MFhlM$PH = N.MFhlM$PH(this.G0.b, i);
            if (i == 16) {
                iArr = new int[]{R.string.f65290_resource_name_obfuscated_RES_2131953846, R.string.f65300_resource_name_obfuscated_RES_2131953847, R.string.f65320_resource_name_obfuscated_RES_2131953849};
            } else {
                iArr = null;
            }
            triStateSiteSettingsPreference.t0 = MFhlM$PH;
            triStateSiteSettingsPreference.u0 = iArr;
        } else if (this.S0) {
            preferenceScreen.f0(chromeSwitchPreference);
            preferenceScreen.f0(triStateSiteSettingsPreference);
            fourStateCookieSettingsPreference.f9480J = this;
            C2525fS fSVar = new C2525fS();
            fSVar.f9922a = N.MJSt3Ocq(this.G0.b, 0);
            PrefService a2 = Wr1.a(this.G0.b);
            fSVar.b = N.MzGf81GW(a2.f10883a, "profile.cookie_controls_mode");
            fSVar.c = this.J0.n();
            fSVar.d = N.MrEgF7hX(a2.f10883a, "profile.cookie_controls_mode");
            if (fourStateCookieSettingsPreference.y0 != null) {
                fourStateCookieSettingsPreference.a0(fSVar);
            } else {
                fourStateCookieSettingsPreference.t0 = fSVar;
            }
        } else {
            preferenceScreen.f0(triStateSiteSettingsPreference);
            preferenceScreen.f0(fourStateCookieSettingsPreference);
            chromeSwitchPreference.f9480J = this;
            chromeSwitchPreference.U(AbstractC1154Sy.g(i));
            BrowserContextHandle browserContextHandle = this.G0.b;
            if (!this.J0.r(9) || !N.M__mL5j3(browserContextHandle)) {
                C1093Ry e = AbstractC1154Sy.e(i);
                int i2 = e.e;
                if (i2 == 0) {
                    i2 = AbstractC1154Sy.a(e.c.intValue());
                }
                chromeSwitchPreference.d0(i2);
            } else {
                chromeSwitchPreference.d0(R.string.f64930_resource_name_obfuscated_RES_2131953810);
            }
            C1093Ry e2 = AbstractC1154Sy.e(i);
            int i3 = e2.f;
            if (i3 == 0) {
                i3 = AbstractC1154Sy.a(e2.d.intValue());
            }
            chromeSwitchPreference.b0(i3);
            C2195dX0 dx0 = new C2195dX0(this, this.G0.a());
            chromeSwitchPreference.B0 = dx0;
            AbstractC1865bc0.b(dx0, chromeSwitchPreference);
            chromeSwitchPreference.a0(N.MJSt3Ocq(browserContextHandle, i));
        }
        if (!this.J0.r(8)) {
            preferenceScreen.f0(preferenceScreen.b0("cookie_info_text"));
        }
        if (q) {
            if (!o1()) {
                ChromeBasePreference chromeBasePreference = new ChromeBasePreference(this.z0.f11127a, null);
                ChromeBasePreference chromeBasePreference2 = new ChromeBasePreference(this.z0.f11127a, null);
                this.J0.b(chromeBasePreference, chromeBasePreference2, x(), true, this.G0.f9887a.getString(R.string.f46950_resource_name_obfuscated_RES_2131952012));
                if (chromeBasePreference.M != null) {
                    chromeBasePreference.O("os_permissions_warning");
                    preferenceScreen.a0(chromeBasePreference);
                }
                if (chromeBasePreference2.M != null) {
                    chromeBasePreference2.O("os_permissions_warning_extra");
                    preferenceScreen.a0(chromeBasePreference2);
                }
            }
            preferenceScreen.f0(b0);
            preferenceScreen.f0(b02);
            preferenceScreen.f0(b03);
            preferenceScreen.f0(hf0);
            preferenceScreen.f0(hf02);
            preferenceScreen.f0(hf03);
            return;
        }
        if (this.J0.r(14)) {
            if (Build.VERSION.SDK_INT < 26) {
                b0.f9480J = this;
            } else {
                preferenceScreen.f0(b0);
            }
            Objects.requireNonNull(this.G0);
            if (N.M09VlOh_("QuietNotificationPrompts")) {
                b02.f9480J = this;
            } else {
                preferenceScreen.f0(b02);
            }
            v1();
        } else {
            preferenceScreen.f0(b0);
            preferenceScreen.f0(b02);
        }
        if (this.J0.r(16)) {
            Objects.requireNonNull(this.G0);
            b03.K = new YW0(this);
            z = false;
            this.H0.setFocusable(false);
        } else {
            z = false;
            preferenceScreen.f0(b03);
            this.H0.setFocusable(true);
        }
        if (!this.L0) {
            this.M0 = z;
            this.N0 = true;
            this.O0 = z;
        }
        this.L0 = true;
        hf0.K = this;
        hf02.K = this;
        hf03.K = this;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        BrowserContextHandle browserContextHandle = this.G0.b;
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.J0 = QX0.e(browserContextHandle, bundle2.getString("category", ""));
        }
        boolean z = false;
        if (this.J0.r(0) || this.J0.r(22)) {
            throw new IllegalArgumentException("Use AllSiteSettings instead.");
        }
        int i = this.J0.i();
        this.R0 = WebsitePreferenceBridge.a(i);
        if (i == 0) {
            z = true;
        }
        this.S0 = z;
        ViewGroup viewGroup2 = (ViewGroup) super.l0(layoutInflater, viewGroup, bundle);
        RecyclerView recyclerView = this.A0;
        this.H0 = recyclerView;
        recyclerView.s0(null);
        i1(null);
        return viewGroup2;
    }

    public final boolean l1() {
        return ((FourStateCookieSettingsPreference) this.z0.g.b0("four_state_cookie_toggle")).c0() == EnumC2354eS.ALLOW;
    }

    public final CharSequence m1(int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(I().getString(i));
        String format = String.format(Locale.getDefault(), " - %d", Integer.valueOf(i2));
        spannableStringBuilder.append((CharSequence) format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(I().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849)), 0, spannableStringBuilder.length() - format.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(I().getColor(R.color.f11660_resource_name_obfuscated_RES_2131099856)), spannableStringBuilder.length() - format.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public final void n1() {
        QX0 qx0 = this.J0;
        if (!(qx0.h() && qx0.g(u()))) {
            r1();
        } else {
            new Dy1(this.G0.b, false).c(this.J0, new C2024cX0(this, null));
        }
    }

    public final boolean o1() {
        if (this.R0) {
            return ((TriStateSiteSettingsPreference) this.z0.g.b0("tri_state_toggle")).t0 == 2;
        }
        if (this.S0) {
            return ((FourStateCookieSettingsPreference) this.z0.g.b0("four_state_cookie_toggle")).c0() == EnumC2354eS.BLOCK;
        }
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) this.z0.g.b0("binary_toggle");
        if (chromeSwitchPreference != null) {
            return !chromeSwitchPreference.t0;
        }
        return false;
    }

    public final /* synthetic */ void p1(C3469ky1 ky1, BrowserContextHandle browserContextHandle, int i, DialogInterface dialogInterface) {
        ky1.l(browserContextHandle, i, 0);
        n1();
        dialogInterface.dismiss();
    }

    @Override // defpackage.AbstractC2324eF0, defpackage.AbstractC4204pF0
    public boolean q(Preference preference) {
        int i = 0;
        if (this.z0.g.b0("binary_toggle") == null || !this.J0.n()) {
            if (preference instanceof Fy1) {
                Fy1 fy1 = (Fy1) preference;
                Objects.requireNonNull(this.G0);
                if (!N.MJ8X0ZQd("PageInfoV2") || fy1.o0.Q.equals("managed_group")) {
                    fy1.S = SingleWebsiteSettings.class.getName();
                    fy1.j().putSerializable("org.chromium.chrome.preferences.site_address", fy1.B0.F);
                    fy1.j().putInt("org.chromium.chrome.preferences.navigation_source", this.K.getInt("org.chromium.chrome.preferences.navigation_source", 0));
                } else {
                    C3469ky1 ky1 = fy1.B0;
                    BrowserContextHandle browserContextHandle = this.G0.b;
                    int i2 = this.J0.i();
                    Integer e = ky1.e(browserContextHandle, i2);
                    String[] strArr = {O(AbstractC1154Sy.f(1)), O(AbstractC1154Sy.f(2))};
                    C2290e4 e4Var = new C2290e4(x(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
                    e4Var.e(R.string.f48470_resource_name_obfuscated_RES_2131952164, null);
                    e4Var.d(R.string.f60190_resource_name_obfuscated_RES_2131953336, new ZW0(this, ky1, browserContextHandle, i2));
                    if (e.intValue() != 1) {
                        i = 1;
                    }
                    DialogInterface$OnClickListenerC1673aX0 ax0 = new DialogInterface$OnClickListenerC1673aX0(this, ky1, browserContextHandle, i2);
                    C1598a4 a4Var = e4Var.f9828a;
                    a4Var.n = strArr;
                    a4Var.p = ax0;
                    a4Var.v = i;
                    a4Var.u = true;
                    e4Var.i();
                }
            }
            return super.q(preference);
        }
        if (this.J0.o()) {
            AbstractC1865bc0.f(x(), new C2195dX0(this, this.G0.a()));
        } else {
            AbstractC1865bc0.e(x());
        }
        return false;
    }

    public final boolean q1() {
        C2427et etVar = this.G0;
        Activity u = u();
        Objects.requireNonNull(etVar);
        C2535fX.a().b(u, u.getString(R.string.f52600_resource_name_obfuscated_RES_2131952577), Profile.b(), null);
        return true;
    }

    public final void r1() {
        int i;
        this.z0.g.e0();
        AbstractC2870hT0.a(this, R.xml.f76460_resource_name_obfuscated_RES_2132213802);
        k1();
        BrowserContextHandle browserContextHandle = this.G0.b;
        int i2 = 0;
        boolean z = true;
        if (!this.J0.r(18) && !this.J0.r(11) && !this.J0.r(8) && ((!this.J0.r(4) || N.MJSt3Ocq(browserContextHandle, 22)) && (!this.J0.r(3) || N.MJSt3Ocq(browserContextHandle, 13)))) {
            z = false;
        }
        if (z) {
            C4375qF0 qf0 = this.z0;
            PreferenceScreen preferenceScreen = qf0.g;
            Context context = qf0.f11127a;
            BrowserContextHandle browserContextHandle2 = this.G0.b;
            if (this.J0.r(3)) {
                i2 = R.string.f64750_resource_name_obfuscated_RES_2131953792;
            } else if (this.J0.r(4)) {
                i2 = R.string.f64760_resource_name_obfuscated_RES_2131953793;
            } else {
                if (this.J0.r(11)) {
                    i = N.MJSt3Ocq(browserContextHandle2, 2) ? R.string.f64800_resource_name_obfuscated_RES_2131953797 : R.string.f64790_resource_name_obfuscated_RES_2131953796;
                } else if (this.J0.r(18)) {
                    i = N.MJSt3Ocq(browserContextHandle2, 31) ? R.string.f64820_resource_name_obfuscated_RES_2131953799 : R.string.f64810_resource_name_obfuscated_RES_2131953798;
                } else if (this.J0.r(8)) {
                    i2 = this.S0 ? R.string.f64770_resource_name_obfuscated_RES_2131953794 : R.string.f64770_resource_name_obfuscated_RES_2131953794;
                    i2 = R.string.f64780_resource_name_obfuscated_RES_2131953795;
                }
                i2 = i;
            }
            preferenceScreen.a0(new C4167p3(context, "add_exception", O(i2), this.J0, this));
        }
    }

    public final void s1(int i, boolean z) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) this.z0.g.b0("allowed_group");
        if (expandablePreferenceGroup != null) {
            if (i == 0) {
                PreferenceScreen preferenceScreen = this.z0.g;
                preferenceScreen.g0(expandablePreferenceGroup);
                preferenceScreen.u();
            } else if (this.L0) {
                expandablePreferenceGroup.V(m1(z ? R.string.f64860_resource_name_obfuscated_RES_2131953803 : R.string.f65450_resource_name_obfuscated_RES_2131953862, i));
                expandablePreferenceGroup.j0(this.N0);
            }
        }
    }

    public final void t1(int i) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) this.z0.g.b0("blocked_group");
        if (i == 0) {
            if (expandablePreferenceGroup != null) {
                PreferenceScreen preferenceScreen = this.z0.g;
                preferenceScreen.g0(expandablePreferenceGroup);
                preferenceScreen.u();
            }
        } else if (this.L0) {
            expandablePreferenceGroup.V(m1(this.J0.r(18) ? R.string.f64880_resource_name_obfuscated_RES_2131953805 : R.string.f64870_resource_name_obfuscated_RES_2131953804, i));
            expandablePreferenceGroup.j0(this.M0);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_id_site_settings_help) {
            if (this.J0.r(16)) {
                C2427et etVar = this.G0;
                Activity u = u();
                Objects.requireNonNull(etVar);
                C2535fX.a().b(u, u.getString(R.string.f52600_resource_name_obfuscated_RES_2131952577), Profile.b(), null);
            } else {
                this.G0.b(u());
            }
            return true;
        }
        boolean z = false;
        if (!RQ0.c(menuItem, this.I0, this.K0, u())) {
            return false;
        }
        String str = this.K0;
        if (str != null && !str.isEmpty()) {
            z = true;
        }
        this.K0 = null;
        if (z) {
            n1();
        }
        return true;
    }

    public final void u1(int i) {
        ExpandablePreferenceGroup expandablePreferenceGroup = (ExpandablePreferenceGroup) this.z0.g.b0("managed_group");
        if (i == 0) {
            if (expandablePreferenceGroup != null) {
                PreferenceScreen preferenceScreen = this.z0.g;
                preferenceScreen.g0(expandablePreferenceGroup);
                preferenceScreen.u();
            }
        } else if (this.L0) {
            expandablePreferenceGroup.V(m1(R.string.f65470_resource_name_obfuscated_RES_2131953864, i));
            expandablePreferenceGroup.j0(this.O0);
        }
    }

    public final void v1() {
        BrowserContextHandle browserContextHandle = this.G0.b;
        Boolean valueOf = Boolean.valueOf(N.MJSt3Ocq(browserContextHandle, 6));
        ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference = (ChromeBaseCheckBoxPreference) this.z0.g.b0("notifications_vibrate");
        if (chromeBaseCheckBoxPreference != null) {
            chromeBaseCheckBoxPreference.K(valueOf.booleanValue());
        }
        Objects.requireNonNull(this.G0);
        if (N.M09VlOh_("QuietNotificationPrompts")) {
            ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference2 = (ChromeBaseCheckBoxPreference) this.z0.g.b0("notifications_quiet_ui");
            if (valueOf.booleanValue()) {
                if (chromeBaseCheckBoxPreference2 == null) {
                    this.z0.g.a0(this.T0);
                    chromeBaseCheckBoxPreference2 = (ChromeBaseCheckBoxPreference) this.z0.g.b0("notifications_quiet_ui");
                }
                chromeBaseCheckBoxPreference2.a0(N.MzIXnlkD(Wr1.a(browserContextHandle).f10883a, "profile.content_settings.enable_quiet_permission_ui.notifications"));
            } else if (chromeBaseCheckBoxPreference2 != null) {
                this.T0 = chromeBaseCheckBoxPreference2;
                PreferenceScreen preferenceScreen = this.z0.g;
                preferenceScreen.g0(chromeBaseCheckBoxPreference2);
                preferenceScreen.u();
            }
        }
    }
}
