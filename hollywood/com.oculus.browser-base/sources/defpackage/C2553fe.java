package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* renamed from: fe  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2553fe implements ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Preference f9937a;
    public final /* synthetic */ AutofillPaymentMethodsFragment b;

    public C2553fe(AutofillPaymentMethodsFragment autofillPaymentMethodsFragment, Preference preference) {
        this.b = autofillPaymentMethodsFragment;
        this.f9937a = preference;
    }

    @Override // org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback
    public void a(boolean z) {
        AutofillPaymentMethodsFragment autofillPaymentMethodsFragment = this.b;
        Preference preference = this.f9937a;
        int i = AutofillPaymentMethodsFragment.G0;
        autofillPaymentMethodsFragment.l1(preference, z);
    }
}
