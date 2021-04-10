package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill_assistant.AutofillAssistantPreferenceFragment;

/* renamed from: yd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5790yd implements XE0 {
    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i = AutofillAssistantPreferenceFragment.G0;
        NU0.f8549a.m("Chrome.Assistant.Enabled", ((Boolean) obj).booleanValue());
        return true;
    }
}
