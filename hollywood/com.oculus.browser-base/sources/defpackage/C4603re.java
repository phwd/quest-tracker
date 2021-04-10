package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;

/* renamed from: re  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4603re extends AbstractC0896Or {
    public C4603re(AutofillProfilesFragment autofillProfilesFragment) {
    }

    @Override // defpackage.AbstractC1467Yb0, defpackage.AbstractC1528Zb0
    public boolean b(Preference preference) {
        return N.MCL0OG9d() && !PersonalDataManager.h();
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return N.MCL0OG9d();
    }
}
