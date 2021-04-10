package org.chromium.chrome.browser.homepage.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;
import org.chromium.components.browser_ui.widget.RadioButtonWithEditText;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RadioButtonGroupHomepagePreference extends Preference implements RadioGroup.OnCheckedChangeListener, RJ0 {
    public boolean t0;
    public RadioButtonWithEditText u0;
    public RadioButtonWithDescription v0;
    public RadioButtonWithDescriptionLayout w0;
    public TextView x0;
    public IJ0 y0;

    public RadioButtonGroupHomepagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41060_resource_name_obfuscated_RES_2131624415;
    }

    public void a0(IJ0 ij0) {
        if (this.t0) {
            this.w0.setEnabled(ij0.c);
            this.x0.setEnabled(ij0.c);
            this.u0.G.setText(ij0.b);
            if (ij0.f8217a == 0) {
                this.v0.f(true);
            } else {
                this.u0.f(true);
            }
            int i = 0;
            this.v0.setVisibility(ij0.d ? 0 : 8);
            RadioButtonWithEditText radioButtonWithEditText = this.u0;
            if (!ij0.e) {
                i = 8;
            }
            radioButtonWithEditText.setVisibility(i);
        }
        this.y0 = ij0;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.y0.f8217a = !this.v0.e();
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        this.v0 = (RadioButtonWithDescription) tf0.x(R.id.radio_button_chrome_ntp);
        this.u0 = (RadioButtonWithEditText) tf0.x(R.id.radio_button_uri_edit);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) tf0.x(R.id.radio_button_group);
        this.w0 = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.G = this;
        this.x0 = (TextView) tf0.x(R.id.title);
        this.t0 = true;
        IJ0 ij0 = this.y0;
        if (ij0 != null) {
            a0(ij0);
        }
        this.u0.L.add(this);
    }
}
