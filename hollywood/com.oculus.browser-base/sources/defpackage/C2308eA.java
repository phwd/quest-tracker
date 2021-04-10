package defpackage;

import J.N;
import androidx.preference.Preference;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchPreferenceFragment;

/* renamed from: eA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2308eA implements XE0 {
    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i = ContextualSearchPreferenceFragment.G0;
        Boolean bool = (Boolean) obj;
        N.MY13p7Sp(ContextualSearchManager.f().f10883a, "search.contextual_search_enabled", bool.booleanValue() ? "true" : "false");
        boolean booleanValue = bool.booleanValue();
        Pattern pattern = AA.f7657a;
        AbstractC3364kK0.g("Search.ContextualSearchPreferenceStateChange", booleanValue ? 1 : 2, 3);
        return true;
    }
}
