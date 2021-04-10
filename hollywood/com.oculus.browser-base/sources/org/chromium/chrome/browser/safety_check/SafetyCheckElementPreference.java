package org.chromium.chrome.browser.safety_check;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafetyCheckElementPreference extends ChromeBasePreference {
    public SafetyCheckElementPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l0 = R.layout.f41240_resource_name_obfuscated_RES_2131624433;
    }

    @Override // androidx.preference.Preference, org.chromium.components.browser_ui.settings.ChromeBasePreference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        tf0.x(R.id.progress);
        ImageView imageView = (ImageView) tf0.x(R.id.status_view);
    }
}
