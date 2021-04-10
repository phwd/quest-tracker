package defpackage;

import J.N;
import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;

/* renamed from: ee  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2382ee extends AbstractC0896Or {
    public C2382ee(AutofillPaymentMethodsFragment autofillPaymentMethodsFragment) {
    }

    @Override // defpackage.AbstractC1467Yb0, defpackage.AbstractC1528Zb0
    public boolean b(Preference preference) {
        return N.MbTLtWkf() && !PersonalDataManager.h();
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return N.MbTLtWkf();
    }
}
