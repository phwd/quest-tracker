package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.datareduction.settings.DataReductionPreferenceFragment;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* renamed from: KC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class KC implements XE0 {
    public final DataReductionPreferenceFragment F;
    public final ChromeSwitchPreference G;

    public KC(DataReductionPreferenceFragment dataReductionPreferenceFragment, ChromeSwitchPreference chromeSwitchPreference) {
        this.F = dataReductionPreferenceFragment;
        this.G = chromeSwitchPreference;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.k1(this.G, obj);
    }
}
