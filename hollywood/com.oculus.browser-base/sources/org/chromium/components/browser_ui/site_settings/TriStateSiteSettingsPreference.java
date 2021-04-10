package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TriStateSiteSettingsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public int t0 = 0;
    public int[] u0;
    public RadioButtonWithDescription v0;
    public RadioButtonWithDescription w0;
    public RadioButtonWithDescription x0;
    public RadioGroup y0;

    public TriStateSiteSettingsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f42170_resource_name_obfuscated_RES_2131624526;
        Q(false);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.v0.e()) {
            this.t0 = 1;
        } else if (this.w0.e()) {
            this.t0 = 3;
        } else if (this.x0.e()) {
            this.t0 = 2;
        }
        f(Integer.valueOf(this.t0));
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        RadioButtonWithDescription radioButtonWithDescription;
        super.x(tf0);
        this.v0 = (RadioButtonWithDescription) tf0.x(R.id.allowed);
        this.w0 = (RadioButtonWithDescription) tf0.x(R.id.ask);
        this.x0 = (RadioButtonWithDescription) tf0.x(R.id.blocked);
        RadioGroup radioGroup = (RadioGroup) tf0.x(R.id.radio_button_layout);
        this.y0 = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        int[] iArr = this.u0;
        if (iArr != null) {
            this.v0.h(this.F.getText(iArr[0]));
            this.w0.h(this.F.getText(this.u0[1]));
            this.x0.h(this.F.getText(this.u0[2]));
        }
        int i = this.t0;
        if (i == 1) {
            radioButtonWithDescription = this.v0;
        } else if (i == 3) {
            radioButtonWithDescription = this.w0;
        } else {
            radioButtonWithDescription = i == 2 ? this.x0 : null;
        }
        if (radioButtonWithDescription != null) {
            radioButtonWithDescription.f(true);
        }
    }
}
