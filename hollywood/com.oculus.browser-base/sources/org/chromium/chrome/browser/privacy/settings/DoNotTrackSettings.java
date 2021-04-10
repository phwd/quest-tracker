package org.chromium.chrome.browser.privacy.settings;

import J.N;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;
import org.chromium.components.prefs.PrefService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DoNotTrackSettings extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76170_resource_name_obfuscated_RES_2132213773);
        u().setTitle(R.string.f50900_resource_name_obfuscated_RES_2131952407);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("do_not_track_switch");
        PrefService a2 = Wr1.a(Profile.b());
        chromeSwitchPreference.a0(N.MzIXnlkD(a2.f10883a, "enable_do_not_track"));
        chromeSwitchPreference.f9480J = new C4888tG(a2);
    }
}
