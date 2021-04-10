package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill_assistant.AutofillAssistantPreferenceFragment;

/* renamed from: wd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5450wd implements XE0 {
    public final AutofillAssistantPreferenceFragment F;

    public C5450wd(AutofillAssistantPreferenceFragment autofillAssistantPreferenceFragment) {
        this.F = autofillAssistantPreferenceFragment;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        return this.F.l1(obj);
    }
}
