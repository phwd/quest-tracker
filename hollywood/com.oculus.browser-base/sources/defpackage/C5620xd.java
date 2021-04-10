package defpackage;

import android.view.View;
import org.chromium.chrome.browser.autofill_assistant.AutofillAssistantPreferenceFragment;

/* renamed from: xd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5620xd extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AutofillAssistantPreferenceFragment f11620a;

    public C5620xd(AutofillAssistantPreferenceFragment autofillAssistantPreferenceFragment) {
        this.f11620a = autofillAssistantPreferenceFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f11620a.m1();
    }
}
