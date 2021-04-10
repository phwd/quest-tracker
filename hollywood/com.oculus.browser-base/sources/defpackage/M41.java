package defpackage;

import android.widget.CompoundButton;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* renamed from: M41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M41 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ ChromeSwitchPreference F;

    public M41(ChromeSwitchPreference chromeSwitchPreference) {
        this.F = chromeSwitchPreference;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.F.f(Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
        } else {
            this.F.a0(z);
        }
    }
}
