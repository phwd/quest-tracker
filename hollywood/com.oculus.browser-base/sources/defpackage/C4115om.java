package defpackage;

import java.util.Comparator;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: om  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4115om implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) obj2;
        boolean z = C2892hd.g((PersonalDataManager.AutofillProfile) obj, 0) == 0;
        boolean z2 = C2892hd.g(autofillProfile, 0) == 0;
        if (z2 == z) {
            return 0;
        }
        if (z2) {
            return 1;
        }
        return -1;
    }
}
