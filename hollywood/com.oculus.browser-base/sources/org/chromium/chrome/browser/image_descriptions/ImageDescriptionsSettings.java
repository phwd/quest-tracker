package org.chromium.chrome.browser.image_descriptions;

import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ImageDescriptionsSettings extends AbstractC2324eF0 implements XE0 {
    public ChromeSwitchPreference G0;
    public RadioButtonGroupAccessibilityPreference H0;
    public boolean I0;
    public boolean J0;
    public Profile K0;

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        if (preference.Q.equals("image_descriptions_switch")) {
            if (((Boolean) obj).booleanValue()) {
                throw null;
            }
            throw null;
        } else if (!preference.Q.equals("image_descriptions_data_policy")) {
            return true;
        } else {
            ((Boolean) obj).booleanValue();
            throw null;
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void b0(Bundle bundle) {
        this.i0 = true;
        u().setTitle(R.string.f52920_resource_name_obfuscated_RES_2131952609);
        i1(null);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76230_resource_name_obfuscated_RES_2132213779);
        this.K0 = Profile.b();
        Bundle bundle2 = this.K;
        if (bundle2 != null) {
            this.I0 = bundle2.getBoolean("image_descriptions_switch");
            this.J0 = bundle2.getBoolean("image_descriptions_data_policy");
        }
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("image_descriptions_switch");
        this.G0 = chromeSwitchPreference;
        chromeSwitchPreference.f9480J = this;
        chromeSwitchPreference.a0(this.I0);
        RadioButtonGroupAccessibilityPreference radioButtonGroupAccessibilityPreference = (RadioButtonGroupAccessibilityPreference) f1("image_descriptions_data_policy");
        this.H0 = radioButtonGroupAccessibilityPreference;
        radioButtonGroupAccessibilityPreference.f9480J = this;
        radioButtonGroupAccessibilityPreference.K(this.I0);
        this.H0.w0 = this.J0;
    }
}
