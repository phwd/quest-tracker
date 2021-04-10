package org.chromium.chrome.browser.autofill.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillEditLinkPreference extends Preference {
    public AutofillEditLinkPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Q(false);
        this.l0 = R.layout.f37000_resource_name_obfuscated_RES_2131624009;
        U(R.string.f47480_resource_name_obfuscated_RES_2131952065);
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
        View x = tf0.x(R.id.preference_click_target);
        x.setClickable(true);
        x.setOnClickListener(new View$OnClickListenerC0375Gd(this));
    }
}
