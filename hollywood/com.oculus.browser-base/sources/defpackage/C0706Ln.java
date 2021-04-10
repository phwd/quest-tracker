package defpackage;

import android.widget.CompoundButton;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;

/* renamed from: Ln  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0706Ln implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ ChromeBaseCheckBoxPreference F;

    public C0706Ln(ChromeBaseCheckBoxPreference chromeBaseCheckBoxPreference) {
        this.F = chromeBaseCheckBoxPreference;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.F.f(Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.F.a0(z);
        }
    }
}
