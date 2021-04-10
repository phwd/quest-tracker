package org.chromium.chrome.browser.privacy.secure_dns;

import J.N;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SecureDnsSettings extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;
    public ChromeSwitchPreference H0;
    public SecureDnsProviderPreference I0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        m1();
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        int MPUFHf86;
        u().setTitle(R.string.f61490_resource_name_obfuscated_RES_2131953466);
        AbstractC2870hT0.a(this, R.xml.f76380_resource_name_obfuscated_RES_2132213794);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("secure_dns_switch");
        this.H0 = chromeSwitchPreference;
        DR0 dr0 = new DR0();
        chromeSwitchPreference.B0 = dr0;
        AbstractC1865bc0.b(dr0, chromeSwitchPreference);
        this.H0.f9480J = new ER0(this);
        if (!N.M_qct0Io() && (MPUFHf86 = N.MPUFHf86()) != 0) {
            boolean z = false;
            this.H0.K(false);
            if (MPUFHf86 == 2) {
                z = true;
            }
            this.H0.b0(z ? R.string.f61480_resource_name_obfuscated_RES_2131953465 : R.string.f61470_resource_name_obfuscated_RES_2131953464);
        }
        SecureDnsProviderPreference secureDnsProviderPreference = (SecureDnsProviderPreference) f1("secure_dns_provider");
        this.I0 = secureDnsProviderPreference;
        secureDnsProviderPreference.f9480J = new FR0(this);
        m1();
    }

    public final boolean k1(Object obj) {
        n1(((Boolean) obj).booleanValue(), this.I0.E0);
        m1();
        return true;
    }

    public final boolean l1(Object obj) {
        CR0 cr0 = (CR0) obj;
        boolean n1 = n1(this.H0.t0, cr0);
        if (n1 == cr0.c) {
            return true;
        }
        SecureDnsProviderPreference secureDnsProviderPreference = this.I0;
        CR0 cr02 = new CR0(cr0.f7809a, cr0.b, n1);
        if (cr02.equals(secureDnsProviderPreference.E0)) {
            return false;
        }
        secureDnsProviderPreference.E0 = cr02;
        secureDnsProviderPreference.c0();
        return false;
    }

    public final void m1() {
        int MvJZm_HK = N.MvJZm_HK();
        boolean z = false;
        boolean z2 = MvJZm_HK != 0;
        boolean z3 = N.M_qct0Io() || N.MPUFHf86() != 0;
        this.H0.a0(z2);
        this.I0.K(z2 && !z3);
        if (MvJZm_HK == 2) {
            z = true;
        }
        String M2_$s1TF = N.M2_$s1TF();
        SecureDnsProviderPreference secureDnsProviderPreference = this.I0;
        CR0 cr0 = new CR0(z, M2_$s1TF, true);
        if (!cr0.equals(secureDnsProviderPreference.E0)) {
            secureDnsProviderPreference.E0 = cr0;
            secureDnsProviderPreference.c0();
        }
    }

    public final boolean n1(boolean z, CR0 cr0) {
        if (!z) {
            N.M7D0A6Nn(0);
            N.McbaC_y9("");
        } else if (!cr0.f7809a) {
            N.M7D0A6Nn(1);
            N.McbaC_y9("");
        } else if (cr0.b.isEmpty() || !N.McbaC_y9(cr0.b)) {
            return false;
        } else {
            N.M7D0A6Nn(2);
        }
        return true;
    }
}
