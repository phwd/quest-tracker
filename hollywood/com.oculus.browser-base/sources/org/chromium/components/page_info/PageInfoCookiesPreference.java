package org.chromium.components.page_info;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.browser_ui.site_settings.SiteSettingsPreferenceFragment;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PageInfoCookiesPreference extends SiteSettingsPreferenceFragment {
    public static final /* synthetic */ int H0 = 0;
    public ChromeSwitchPreference I0;
    public ChromeImageViewPreference J0;
    public Runnable K0;
    public Dialog L0;
    public boolean M0;
    public boolean N0;

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        if (this.G0 == null) {
            C0317Fe fe = new C0317Fe(G());
            fe.p(this);
            fe.e();
            return;
        }
        AbstractC2870hT0.a(this, R.xml.f76320_resource_name_obfuscated_RES_2132213788);
        this.I0 = (ChromeSwitchPreference) f1("cookie_switch");
        this.J0 = (ChromeImageViewPreference) f1("cookie_in_use");
    }

    public final boolean k1() {
        if (this.M0 || !this.N0) {
            return true;
        }
        C2290e4 e4Var = new C2290e4(x(), R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f57000_resource_name_obfuscated_RES_2131953017);
        e4Var.c(R.string.f57010_resource_name_obfuscated_RES_2131953018);
        e4Var.e(R.string.f57020_resource_name_obfuscated_RES_2131953019, new DialogInterface$OnClickListenerC3801mv0(this));
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, new DialogInterface$OnClickListenerC3972nv0(this));
        this.L0 = e4Var.i();
        return true;
    }

    public final /* synthetic */ void l1() {
        this.K0.run();
    }

    public final /* synthetic */ void m1() {
        this.L0 = null;
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void n0() {
        super.n0();
        Dialog dialog = this.L0;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void n1(int i, boolean z) {
        boolean z2 = i != 2;
        boolean z3 = i == 1;
        this.I0.W(z2);
        if (z2) {
            ChromeSwitchPreference chromeSwitchPreference = this.I0;
            Drawable b = AbstractC2870hT0.b(x(), R.drawable.f30190_resource_name_obfuscated_RES_2131231059);
            if (chromeSwitchPreference.P != b) {
                chromeSwitchPreference.P = b;
                chromeSwitchPreference.O = 0;
                chromeSwitchPreference.s();
            }
            this.I0.a0(z3);
            this.I0.K(!z);
        }
    }

    public void o1(int i, int i2) {
        String str;
        ChromeSwitchPreference chromeSwitchPreference = this.I0;
        boolean z = false;
        if (i2 > 0) {
            str = x().getResources().getQuantityString(R.plurals.f42680_resource_name_obfuscated_RES_2131820560, i2, Integer.valueOf(i2));
        } else {
            str = null;
        }
        chromeSwitchPreference.T(str);
        this.J0.V(x().getResources().getQuantityString(R.plurals.f42810_resource_name_obfuscated_RES_2131820573, i, Integer.valueOf(i)));
        boolean z2 = this.N0;
        if (i != 0) {
            z = true;
        }
        this.N0 = z2 | z;
        p1();
    }

    public final void p1() {
        ChromeImageViewPreference chromeImageViewPreference = this.J0;
        int i = (this.M0 || !this.N0) ? R.color.f11270_resource_name_obfuscated_RES_2131099817 : R.color.f11230_resource_name_obfuscated_RES_2131099813;
        if (chromeImageViewPreference.w0 != i) {
            chromeImageViewPreference.w0 = i;
            chromeImageViewPreference.a0();
        }
    }
}
