package org.chromium.chrome.browser.privacy.settings;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.chrome.browser.sync.settings.GoogleServicesSettings;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrivacySettings extends AbstractC2324eF0 implements XE0 {
    public static final String[] G0 = {"clear_browsing_data", "safe_browsing", "can_make_payment", "preload_pages", "usage_stats_reporting", "secure_dns", "do_not_track", "privacy_sandbox", "sync_and_services_link"};
    public AbstractC1528Zb0 H0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        o1();
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        String str = preference.Q;
        if ("can_make_payment".equals(str)) {
            PrefService a2 = Wr1.a(Profile.b());
            N.Mf2ABpoH(a2.f10883a, "payments.can_make_payment_enabled", ((Boolean) obj).booleanValue());
            return true;
        } else if (!"preload_pages".equals(str)) {
            return true;
        } else {
            WF0 a3 = WF0.a();
            boolean booleanValue = ((Boolean) obj).booleanValue();
            Objects.requireNonNull(a3);
            N.MHe7iQ8a(booleanValue);
            return true;
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        SpannableString spannableString;
        WF0.a();
        AbstractC2870hT0.a(this, R.xml.f76330_resource_name_obfuscated_RES_2132213789);
        if (N.M09VlOh_("PrivacyReorderedAndroid")) {
            int i = 0;
            while (true) {
                String[] strArr = G0;
                if (i >= strArr.length) {
                    break;
                }
                f1(strArr[i]).P(i);
                i++;
            }
        }
        if (!N.M09VlOh_("PrivacySandboxSettings")) {
            this.z0.g.f0(f1("privacy_sandbox"));
        }
        if (N.M09VlOh_("SafeBrowsingSecuritySectionUIAndroid")) {
            u().setTitle(R.string.f59170_resource_name_obfuscated_RES_2131953234);
            Preference f1 = f1("safe_browsing");
            f1.T(SafeBrowsingSettingsFragment.m1(x()));
            f1.K = new XF0();
        } else {
            u().setTitle(R.string.f59150_resource_name_obfuscated_RES_2131953232);
            this.z0.g.f0(f1("safe_browsing"));
        }
        V0(true);
        this.H0 = new C1986cG0();
        ((ChromeSwitchPreference) f1("can_make_payment")).f9480J = this;
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("preload_pages");
        Objects.requireNonNull(WF0.a());
        chromeSwitchPreference.a0(N.MBIqJabw());
        chromeSwitchPreference.f9480J = this;
        AbstractC1528Zb0 zb0 = this.H0;
        chromeSwitchPreference.B0 = zb0;
        AbstractC1865bc0.b(zb0, chromeSwitchPreference);
        f1("secure_dns").W(N.M6bsIDpc("DnsOverHttps", "ShowUi", true));
        Preference f12 = f1("sync_and_services_link");
        C2528fT0 ft0 = new C2528fT0();
        if (!N.M09VlOh_("MobileIdentityConsistency")) {
            spannableString = FY0.a(O(R.string.f59520_resource_name_obfuscated_RES_2131953269), new EY0("<link>", "</link>", new C4467qp0(I(), new YF0(this, ft0))));
        } else {
            C4467qp0 qp0 = new C4467qp0(I(), new ZF0(this, ft0));
            if (AbstractC2531fV.m(C5949zZ.a(), 1) == null) {
                spannableString = FY0.a(O(R.string.f59530_resource_name_obfuscated_RES_2131953270), new EY0("<link>", "</link>", qp0));
            } else {
                spannableString = FY0.a(O(R.string.f59540_resource_name_obfuscated_RES_2131953271), new EY0("<link1>", "</link1>", new C4467qp0(I(), new C1635aG0(this, ft0))), new EY0("<link2>", "</link2>", qp0));
            }
        }
        f12.T(spannableString);
        o1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, u().getTheme()));
    }

    public final /* synthetic */ void k1(C2528fT0 ft0) {
        ft0.b(u(), SyncAndServicesSettings.class, SyncAndServicesSettings.l1(false));
    }

    public final void l1(C2528fT0 ft0) {
        ft0.b(u(), GoogleServicesSettings.class, null);
    }

    public final /* synthetic */ void m1(C2528fT0 ft0) {
        ft0.b(u(), ManageSyncSettings.class, ManageSyncSettings.m1(false));
    }

    public final boolean n1() {
        Activity u = u();
        C5834yr1 yr1 = new C5834yr1(u, true, new C2157dG0(this));
        Resources resources = u.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, new C5664xr1(yr1));
        hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
        if (yr1.d) {
            hh0.d(AbstractC3258jl0.c, resources, R.string.f64180_resource_name_obfuscated_RES_2131953735);
            hh0.d(AbstractC3258jl0.e, resources, R.string.f64170_resource_name_obfuscated_RES_2131953734);
            hh0.d(AbstractC3258jl0.g, resources, R.string.f60190_resource_name_obfuscated_RES_2131953336);
        } else {
            hh0.d(AbstractC3258jl0.c, resources, R.string.f64160_resource_name_obfuscated_RES_2131953733);
            hh0.d(AbstractC3258jl0.e, resources, R.string.f64150_resource_name_obfuscated_RES_2131953732);
            hh0.d(AbstractC3258jl0.g, resources, R.string.f61790_resource_name_obfuscated_RES_2131953496);
        }
        yr1.c = hh0.a();
        C2746gl0 gl0 = new C2746gl0(new J9(yr1.f11706a), 0);
        yr1.b = gl0;
        gl0.i(yr1.c, 0, false);
        return true;
    }

    public void o1() {
        String str;
        PrefService a2 = Wr1.a(Profile.b());
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("can_make_payment");
        if (chromeSwitchPreference != null) {
            chromeSwitchPreference.a0(N.MzIXnlkD(a2.f10883a, "payments.can_make_payment_enabled"));
        }
        Preference f1 = f1("do_not_track");
        if (f1 != null) {
            f1.S(N.MzIXnlkD(a2.f10883a, "enable_do_not_track") ? R.string.f63330_resource_name_obfuscated_RES_2131953650 : R.string.f63320_resource_name_obfuscated_RES_2131953649);
        }
        Preference f12 = f1("secure_dns");
        if (f12 != null && f12.c0) {
            Context x = x();
            int MvJZm_HK = N.MvJZm_HK();
            if (MvJZm_HK == 0) {
                str = x.getString(R.string.f63320_resource_name_obfuscated_RES_2131953649);
            } else if (MvJZm_HK == 1) {
                str = x.getString(R.string.f61380_resource_name_obfuscated_RES_2131953455);
            } else {
                String M2_$s1TF = N.M2_$s1TF();
                List a3 = AbstractC5764yR0.a();
                int i = 0;
                while (true) {
                    ArrayList arrayList = (ArrayList) a3;
                    if (i >= arrayList.size()) {
                        break;
                    }
                    C5594xR0 xr0 = (C5594xR0) arrayList.get(i);
                    if (xr0.b.equals(M2_$s1TF)) {
                        M2_$s1TF = xr0.f11608a;
                        break;
                    }
                    i++;
                }
                str = String.format("%s - %s", x.getString(R.string.f63330_resource_name_obfuscated_RES_2131953650), M2_$s1TF);
            }
            f12.T(str);
        }
        Preference f13 = f1("safe_browsing");
        if (f13 != null && f13.c0) {
            f13.T(SafeBrowsingSettingsFragment.m1(x()));
        }
        Preference f14 = f1("usage_stats_reporting");
        if (f14 == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 29 || !N.MzIXnlkD(a2.f10883a, "usage_stats_reporting.enabled")) {
            PreferenceScreen preferenceScreen = this.z0.g;
            preferenceScreen.g0(f14);
            preferenceScreen.u();
            return;
        }
        f14.K = new C1815bG0(this);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        C2535fX.a().b(u(), O(R.string.f52590_resource_name_obfuscated_RES_2131952576), Profile.b(), null);
        return true;
    }
}
