package org.chromium.chrome.browser.contextualsearch;

import J.N;
import android.os.Bundle;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.settings.ChromeSwitchPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextualSearchPreferenceFragment extends AbstractC2324eF0 {
    public static final /* synthetic */ int G0 = 0;

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, N.M09VlOh_("ContextualSearchLongpressResolve") ? R.xml.f76120_resource_name_obfuscated_RES_2132213768 : R.xml.f76130_resource_name_obfuscated_RES_2132213769);
        u().setTitle(R.string.f50160_resource_name_obfuscated_RES_2131952333);
        V0(true);
        ChromeSwitchPreference chromeSwitchPreference = (ChromeSwitchPreference) f1("contextual_search_switch");
        chromeSwitchPreference.a0(true ^ ContextualSearchManager.j());
        chromeSwitchPreference.f9480J = new C2308eA();
        C2479fA fAVar = new C2479fA();
        chromeSwitchPreference.B0 = fAVar;
        AbstractC1865bc0.b(fAVar, chromeSwitchPreference);
    }
}
