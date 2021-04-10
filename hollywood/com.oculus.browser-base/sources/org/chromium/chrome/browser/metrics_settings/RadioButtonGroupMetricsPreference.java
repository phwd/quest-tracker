package org.chromium.chrome.browser.metrics_settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RadioButtonGroupMetricsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public RadioButtonGroupMetricsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f41070_resource_name_obfuscated_RES_2131624416;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }
}
