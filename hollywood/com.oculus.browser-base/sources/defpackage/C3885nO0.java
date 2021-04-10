package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;

/* renamed from: nO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3885nO0 extends AbstractC0896Or {
    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        int i = SafeBrowsingSettingsFragment.G0;
        String str = preference.Q;
        if ("text_managed".equals(str) || "safe_browsing_radio_button_group".equals(str)) {
            return N.MAU7_6Tq();
        }
        return false;
    }
}
