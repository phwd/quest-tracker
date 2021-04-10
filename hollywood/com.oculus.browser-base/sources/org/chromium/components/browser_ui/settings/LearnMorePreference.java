package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LearnMorePreference extends Preference {
    public LearnMorePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        U(R.string.f54060_resource_name_obfuscated_RES_2131952723);
        Q(false);
        R(false);
    }

    public final void a0() {
        this.K.d(this);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        TextView textView = (TextView) tf0.x(16908310);
        AbstractC3153j7.i(textView, R.style.f71800_resource_name_obfuscated_RES_2132017753);
        textView.setClickable(true);
        textView.setOnClickListener(new T70(this));
    }
}
