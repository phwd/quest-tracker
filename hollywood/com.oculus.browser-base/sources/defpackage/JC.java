package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.datareduction.settings.DataReductionPreferenceFragment;

/* renamed from: JC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JC implements YE0 {
    public final DataReductionPreferenceFragment F;

    public JC(DataReductionPreferenceFragment dataReductionPreferenceFragment) {
        this.F = dataReductionPreferenceFragment;
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        return this.F.l1();
    }
}
