package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchPreferenceFragment;

/* renamed from: fA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2479fA extends AbstractC0896Or {
    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        int i = ContextualSearchPreferenceFragment.G0;
        return N.MrEgF7hX(ContextualSearchManager.f().f10883a, "search.contextual_search_enabled") && ContextualSearchManager.j();
    }
}
