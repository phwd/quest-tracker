package org.chromium.chrome.browser.image_descriptions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonGroupAccessibilityPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonWithDescriptionLayout t0;
    public RadioButtonWithDescription u0;
    public RadioButtonWithDescription v0;
    public boolean w0;

    public RadioButtonGroupAccessibilityPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41050_resource_name_obfuscated_RES_2131624414;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        boolean e = this.u0.e();
        this.w0 = e;
        f(Boolean.valueOf(e));
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        this.u0 = (RadioButtonWithDescription) tf0.x(R.id.image_descriptions_settings_only_on_wifi_radio_button);
        this.v0 = (RadioButtonWithDescription) tf0.x(R.id.image_descriptions_settings_mobile_data_radio_button);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) this.u0.getParent();
        this.t0 = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.G = this;
        if (this.w0) {
            this.u0.f(true);
        } else {
            this.v0.f(true);
        }
    }
}
