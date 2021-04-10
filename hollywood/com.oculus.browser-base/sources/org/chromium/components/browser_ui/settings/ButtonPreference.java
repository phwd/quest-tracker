package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ButtonPreference extends Preference {
    public ButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f37220_resource_name_obfuscated_RES_2131624031;
        this.l0 = R.layout.f37210_resource_name_obfuscated_RES_2131624030;
        Q(false);
    }

    public final void a0() {
        YE0 ye0 = this.K;
        if (ye0 != null) {
            ye0.d(this);
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        Button button = (Button) tf0.x(R.id.button_preference);
        button.setText(this.M);
        button.setOnClickListener(new View$OnClickListenerC0700Lk(this));
    }
}
