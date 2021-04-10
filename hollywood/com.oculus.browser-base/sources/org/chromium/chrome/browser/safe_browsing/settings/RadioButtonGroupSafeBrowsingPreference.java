package org.chromium.chrome.browser.safe_browsing.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionAndAuxButton;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonGroupSafeBrowsingPreference extends Preference implements RadioGroup.OnCheckedChangeListener, NJ0 {
    public AbstractC1528Zb0 A0;
    public RadioButtonWithDescriptionAndAuxButton t0;
    public RadioButtonWithDescriptionAndAuxButton u0;
    public RadioButtonWithDescription v0;
    public int w0;
    public boolean x0;
    public int y0;
    public JJ0 z0;

    public RadioButtonGroupSafeBrowsingPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41080_resource_name_obfuscated_RES_2131624417;
    }

    public void a0(int i) {
        this.w0 = i;
        boolean z = false;
        if (this.x0) {
            this.t0.f(i == 2);
        }
        this.u0.f(i == 1);
        RadioButtonWithDescription radioButtonWithDescription = this.v0;
        if (i == 0) {
            z = true;
        }
        radioButtonWithDescription.f(z);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (this.x0 && i == this.t0.getId()) {
            this.w0 = 2;
        } else if (i == this.u0.getId()) {
            this.w0 = 1;
        } else if (i == this.v0.getId()) {
            this.w0 = 0;
        }
        f(Integer.valueOf(this.w0));
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        if (this.x0) {
            RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton = (RadioButtonWithDescriptionAndAuxButton) tf0.x(R.id.enhanced_protection);
            this.t0 = radioButtonWithDescriptionAndAuxButton;
            if (this.y0 == 3) {
                Context context = this.F;
                Object obj = K2.f8337a;
                radioButtonWithDescriptionAndAuxButton.setBackgroundColor(context.getColor(R.color.f14630_resource_name_obfuscated_RES_2131100153));
            }
            this.t0.setVisibility(0);
            RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton2 = this.t0;
            radioButtonWithDescriptionAndAuxButton2.K = this;
            radioButtonWithDescriptionAndAuxButton2.L.setOnClickListener(new MJ0(radioButtonWithDescriptionAndAuxButton2));
        }
        RadioButtonWithDescriptionAndAuxButton radioButtonWithDescriptionAndAuxButton3 = (RadioButtonWithDescriptionAndAuxButton) tf0.x(R.id.standard_protection);
        this.u0 = radioButtonWithDescriptionAndAuxButton3;
        radioButtonWithDescriptionAndAuxButton3.K = this;
        radioButtonWithDescriptionAndAuxButton3.L.setOnClickListener(new MJ0(radioButtonWithDescriptionAndAuxButton3));
        RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) tf0.x(R.id.no_protection);
        this.v0 = radioButtonWithDescription;
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) radioButtonWithDescription.getRootView();
        radioButtonWithDescriptionLayout.G = this;
        a0(this.w0);
        if (this.A0.b(this)) {
            radioButtonWithDescriptionLayout.setEnabled(false);
            if (this.x0) {
                this.t0.L.setEnabled(true);
            }
            this.u0.L.setEnabled(true);
        }
    }
}
