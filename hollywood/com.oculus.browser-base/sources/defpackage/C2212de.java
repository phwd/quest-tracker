package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;

/* renamed from: de  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2212de implements XE0 {
    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i = AutofillPaymentMethodsFragment.G0;
        N.Mf2ABpoH(PersonalDataManager.d().f10883a, "autofill.credit_card_fido_auth_enabled", ((Boolean) obj).booleanValue());
        return true;
    }
}
