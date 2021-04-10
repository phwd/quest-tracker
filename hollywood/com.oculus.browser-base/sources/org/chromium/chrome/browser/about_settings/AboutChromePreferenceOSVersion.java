package org.chromium.chrome.browser.about_settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AboutChromePreferenceOSVersion extends Preference {
    public AboutChromePreferenceOSVersion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        if (!Ks1.b()) {
            LayoutInflater.from(this.F).inflate(R.layout.f40150_resource_name_obfuscated_RES_2131624324, (ViewGroup) tf0.x(16908304).getParent(), true);
        }
    }
}
