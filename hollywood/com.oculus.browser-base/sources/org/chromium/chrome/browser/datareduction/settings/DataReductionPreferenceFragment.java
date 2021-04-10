package org.chromium.chrome.browser.datareduction.settings;

import J.N;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.oculus.browser.R;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DataReductionPreferenceFragment extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;
    public boolean H0;
    public boolean I0;
    public boolean J0;
    public boolean K0;
    public boolean L0;

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76140_resource_name_obfuscated_RES_2132213770);
        u().setTitle(R.string.f50630_resource_name_obfuscated_RES_2131952380);
        boolean e = DataReductionProxySettings.d().e();
        this.H0 = !e;
        this.I0 = e;
        m1(e);
        V0(true);
        this.J0 = U20.c(this.K, "FromMainMenu", false);
        this.K0 = U20.c(this.K, "FromInfoBar", false);
        this.L0 = U20.c(this.K, "FromLiteModeHttpsImageCompressionInfoBar", false);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, u().getTheme()));
    }

    public final boolean k1(ChromeSwitchPreference chromeSwitchPreference, Object obj) {
        DataReductionProxySettings d = DataReductionProxySettings.d();
        Context context = chromeSwitchPreference.F;
        Boolean bool = (Boolean) obj;
        d.g(bool.booleanValue());
        m1(bool.booleanValue());
        return true;
    }

    public final /* synthetic */ boolean l1() {
        C2535fX.a().b(u(), O(R.string.f52510_resource_name_obfuscated_RES_2131952568), Profile.b(), null);
        return true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        int i;
        this.i0 = true;
        if (this.I0 && !this.H0) {
            PU0 pu0 = NU0.f8549a;
            pu0.m("displayed_data_reduction_infobar_promo", true);
            pu0.p("displayed_data_reduction_infobar_promo_version", N.MMSdy2S5());
        }
        if (this.J0) {
            i = this.I0 ? this.H0 ? 19 : 18 : this.H0 ? 17 : 16;
        } else if (this.K0) {
            i = this.I0 ? this.H0 ? 31 : 30 : this.H0 ? 29 : 28;
        } else if (this.L0) {
            i = this.I0 ? this.H0 ? 35 : 34 : this.H0 ? 33 : 32;
        } else if (this.I0) {
            i = this.H0 ? 8 : 7;
        } else {
            i = this.H0 ? 6 : 5;
        }
        UC.a(i);
    }

    public void m1(boolean z) {
        if (this.H0 != z) {
            this.z0.g.e0();
            ChromeSwitchPreference chromeSwitchPreference = new ChromeSwitchPreference(this.z0.f11127a, null);
            chromeSwitchPreference.O("data_reduction_switch");
            chromeSwitchPreference.d0(R.string.f63330_resource_name_obfuscated_RES_2131953650);
            chromeSwitchPreference.b0(R.string.f63320_resource_name_obfuscated_RES_2131953649);
            chromeSwitchPreference.f9480J = new KC(this, chromeSwitchPreference);
            LC lc = new LC();
            chromeSwitchPreference.B0 = lc;
            AbstractC1865bc0.b(lc, chromeSwitchPreference);
            this.z0.g.a0(chromeSwitchPreference);
            chromeSwitchPreference.a0(z);
            if (z) {
                AbstractC2870hT0.a(this, R.xml.f76140_resource_name_obfuscated_RES_2132213770);
            } else {
                AbstractC2870hT0.a(this, R.xml.f76150_resource_name_obfuscated_RES_2132213771);
                f1("data_reduction_learn_more").K = new JC(this);
            }
            this.H0 = z;
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void onConfigurationChanged(Configuration configuration) {
        this.A0.T.F.b();
        this.i0 = true;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        C2535fX.a().b(u(), O(R.string.f52510_resource_name_obfuscated_RES_2131952568), Profile.b(), null);
        return true;
    }
}
