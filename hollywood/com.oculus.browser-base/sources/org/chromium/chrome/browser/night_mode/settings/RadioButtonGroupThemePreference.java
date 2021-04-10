package org.chromium.chrome.browser.night_mode.settings;

import J.N;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonGroupThemePreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public int t0;
    public RadioButtonWithDescription u0;
    public RadioButtonWithDescriptionLayout v0;
    public ArrayList w0 = new ArrayList(Collections.nCopies(3, null));
    public LinearLayout x0;
    public boolean y0;
    public CheckBox z0;

    public RadioButtonGroupThemePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41090_resource_name_obfuscated_RES_2131624418;
    }

    public final /* synthetic */ void a0() {
        CheckBox checkBox = this.z0;
        checkBox.setChecked(!checkBox.isChecked());
        f(Integer.valueOf(this.t0));
    }

    public final void b0() {
        if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting")) {
            int i = this.t0;
            if (i == 0 || i == 2) {
                this.v0.b(this.x0, this.u0);
                this.x0.setVisibility(0);
                return;
            }
            this.x0.setVisibility(8);
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 0;
        while (true) {
            if (i2 >= 3) {
                break;
            } else if (((RadioButtonWithDescription) this.w0.get(i2)).e()) {
                this.t0 = i2;
                this.u0 = (RadioButtonWithDescription) this.w0.get(i2);
                break;
            } else {
                i2++;
            }
        }
        b0();
        f(Integer.valueOf(this.t0));
        int i3 = this.t0;
        if (i3 == 0) {
            AbstractC3535lK0.a("Android.DarkTheme.Preference.SystemDefault");
        } else if (i3 == 1) {
            AbstractC3535lK0.a("Android.DarkTheme.Preference.Light");
        } else if (i3 == 2) {
            AbstractC3535lK0.a("Android.DarkTheme.Preference.Dark");
        }
        AbstractC3364kK0.g("Android.DarkTheme.Preference.State", i3, 3);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        this.x0 = (LinearLayout) tf0.x(R.id.checkbox_container);
        this.z0 = (CheckBox) tf0.x(R.id.darken_websites);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) tf0.x(R.id.radio_button_layout);
        this.v0 = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.G = this;
        this.x0.setOnClickListener(new KJ0(this));
        this.z0.setChecked(this.y0);
        this.w0.set(0, (RadioButtonWithDescription) tf0.x(R.id.system_default));
        if (Build.VERSION.SDK_INT >= 29) {
            ((RadioButtonWithDescription) this.w0.get(0)).h(this.F.getString(R.string.f63370_resource_name_obfuscated_RES_2131953654));
        }
        this.w0.set(1, (RadioButtonWithDescription) tf0.x(R.id.light));
        this.w0.set(2, (RadioButtonWithDescription) tf0.x(R.id.dark));
        RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) this.w0.get(this.t0);
        this.u0 = radioButtonWithDescription;
        radioButtonWithDescription.f(true);
        b0();
    }
}
