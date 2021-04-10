package org.chromium.chrome.browser.autofill_assistant;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.settings.GoogleServicesSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillAssistantPreferenceFragment extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;
    public final PU0 H0 = NU0.f8549a;
    public PreferenceCategory I0;
    public ChromeSwitchPreference J0;
    public ChromeSwitchPreference K0;
    public ChromeSwitchPreference L0;
    public Preference M0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        o1();
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76090_resource_name_obfuscated_RES_2132213765);
        u().setTitle(R.string.f59110_resource_name_obfuscated_RES_2131953228);
        this.I0 = (PreferenceCategory) f1("web_assistance");
        if (!(N.M09VlOh_("AutofillAssistantProactiveHelp") || n1())) {
            this.I0.W(false);
        }
        this.J0 = (ChromeSwitchPreference) f1("autofill_assistant_switch");
        if (n1()) {
            this.J0.f9480J = new C5280vd(this);
        } else {
            this.J0.W(false);
        }
        this.K0 = (ChromeSwitchPreference) f1("proactive_help_switch");
        if (N.M09VlOh_("AutofillAssistantProactiveHelp")) {
            this.K0.f9480J = new C5450wd(this);
        } else {
            this.K0.W(false);
        }
        this.M0 = f1("google_services_settings_link");
        this.M0.T(FY0.a(O(R.string.f59180_resource_name_obfuscated_RES_2131953235), new EY0("<link>", "</link>", new C4467qp0(I(), new C5620xd(this)))));
        PreferenceCategory preferenceCategory = (PreferenceCategory) f1("voice_assistance");
        this.L0 = (ChromeSwitchPreference) f1("voice_assistance_enabled");
        if (N.M09VlOh_("OmniboxAssistantVoiceSearch")) {
            this.L0.f9480J = new C5790yd();
        } else {
            preferenceCategory.W(false);
            this.L0.W(false);
        }
        o1();
    }

    public final /* synthetic */ boolean k1(Object obj) {
        this.H0.m("autofill_assistant_switch", ((Boolean) obj).booleanValue());
        o1();
        return true;
    }

    public final /* synthetic */ boolean l1(Object obj) {
        this.H0.m("Chrome.AutofillAssistant.ProactiveHelp", ((Boolean) obj).booleanValue());
        o1();
        return true;
    }

    public final void m1() {
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            Activity u = u();
            String name = GoogleServicesSettings.class.getName();
            Intent intent = new Intent();
            intent.setClass(u, XS0.class);
            if (!(u instanceof Activity)) {
                intent.addFlags(268435456);
                intent.addFlags(67108864);
            }
            if (name != null) {
                intent.putExtra("show_fragment", name);
            }
            U20.q(u, intent);
            return;
        }
        Activity u2 = u();
        Bundle l1 = SyncAndServicesSettings.l1(false);
        String name2 = SyncAndServicesSettings.class.getName();
        Intent intent2 = new Intent();
        intent2.setClass(u2, XS0.class);
        if (!(u2 instanceof Activity)) {
            intent2.addFlags(268435456);
            intent2.addFlags(67108864);
        }
        if (name2 != null) {
            intent2.putExtra("show_fragment", name2);
        }
        intent2.putExtra("show_fragment_args", l1);
        U20.q(u2, intent2);
    }

    public final boolean n1() {
        if (N.M09VlOh_("AutofillAssistant")) {
            this.H0.f8694a.a("autofill_assistant_switch");
            if (AbstractC3983nz.f10523a.contains("autofill_assistant_switch")) {
                return true;
            }
        }
        return false;
    }

    public final void o1() {
        boolean z;
        PU0 pu0 = NU0.f8549a;
        boolean z2 = true;
        boolean d = pu0.d("autofill_assistant_switch", true);
        this.J0.a0(d);
        boolean z3 = !this.J0.c0 || d;
        boolean Mfmn09fr = N.Mfmn09fr(Profile.b());
        boolean d2 = pu0.d("Chrome.AutofillAssistant.ProactiveHelp", true);
        if (N.M09VlOh_("AutofillAssistantDisableProactiveHelpTiedToMSBB")) {
            z = false;
        } else {
            boolean z4 = Mfmn09fr && z3;
            z = !z4 && z3;
            z3 = z4;
        }
        this.K0.K(z3);
        ChromeSwitchPreference chromeSwitchPreference = this.K0;
        if (!z3 || !d2) {
            z2 = false;
        }
        chromeSwitchPreference.a0(z2);
        this.M0.W(z);
        this.L0.a0(this.H0.d("Chrome.Assistant.Enabled", false));
    }
}
