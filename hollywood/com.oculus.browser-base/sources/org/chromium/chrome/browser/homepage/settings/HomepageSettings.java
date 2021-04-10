package org.chromium.chrome.browser.homepage.settings;

import android.os.Bundle;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HomepageSettings extends AbstractC2324eF0 {
    public QX G0;
    public RadioButtonGroupHomepagePreference H0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        RadioButtonGroupHomepagePreference radioButtonGroupHomepagePreference = this.H0;
        if (radioButtonGroupHomepagePreference != null) {
            radioButtonGroupHomepagePreference.a0(k1());
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void E0() {
        super.E0();
        IJ0 ij0 = this.H0.y0;
        if (!SX.d()) {
            boolean z = ij0.f8217a == 0;
            String i = AbstractC1911br1.a(ij0.b).i();
            boolean equals = QX.a().equals(i);
            QX qx = this.G0;
            boolean e = qx.e();
            boolean f = qx.f();
            String d = qx.d();
            if (z != e || equals != f || !d.equals(i)) {
                if (z != e) {
                    qx.b.m("Chrome.Homepage.UseNTP", z);
                }
                if (f != equals) {
                    qx.b.m("homepage_partner_enabled", equals);
                }
                if (!d.equals(i)) {
                    qx.b.p("homepage_custom_uri", i);
                }
                AbstractC3535lK0.a("Settings.Homepage.LocationChanged_V2");
                qx.h();
            }
        }
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        this.G0 = QX.c();
        u().setTitle(R.string.f56820_resource_name_obfuscated_RES_2131952999);
        AbstractC2870hT0.a(this, R.xml.f76220_resource_name_obfuscated_RES_2132213778);
        VX vx = new VX(null);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("homepage_switch");
        chromeSwitchPreference.B0 = vx;
        AbstractC1865bc0.b(vx, chromeSwitchPreference);
        this.H0 = (RadioButtonGroupHomepagePreference) f1("homepage_radio_group");
        chromeSwitchPreference.a0(QX.g());
        chromeSwitchPreference.f9480J = new TX(this);
        this.H0.a0(k1());
        AbstractC3535lK0.a("Settings.Homepage.Opened");
    }

    public final IJ0 k1() {
        boolean z;
        String str;
        String a2;
        boolean d = SX.d();
        if (d) {
            z = AbstractC5154ur1.g(SX.b());
        } else {
            z = this.G0.e() || (this.G0.f() && AbstractC5154ur1.g(QX.a()));
        }
        int i = !z ? 1 : 0;
        boolean z2 = !d && QX.g();
        boolean z3 = !d || z;
        boolean z4 = !d || !z;
        if (SX.d()) {
            a2 = SX.b();
        } else {
            a2 = QX.a();
            String d2 = this.G0.d();
            if (this.G0.f()) {
                if (AbstractC5154ur1.g(a2)) {
                    a2 = "";
                }
            } else if (!TextUtils.isEmpty(d2) || AbstractC5154ur1.g(a2)) {
                str = d2;
                return new IJ0(i, str, z2, z3, z4);
            }
        }
        str = a2;
        return new IJ0(i, str, z2, z3, z4);
    }

    public final boolean l1(Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        QX qx = this.G0;
        qx.b.m("homepage", booleanValue);
        qx.h();
        this.H0.a0(k1());
        return true;
    }
}
