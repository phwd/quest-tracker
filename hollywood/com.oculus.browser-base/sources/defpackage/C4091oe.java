package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;

/* renamed from: oe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4091oe implements XE0 {
    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i = AutofillProfilesFragment.G0;
        N.Mf2ABpoH(PersonalDataManager.d().f10883a, "autofill.profile_enabled", ((Boolean) obj).booleanValue());
        return true;
    }
}
