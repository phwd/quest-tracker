package org.chromium.chrome.browser.payments;

import org.chromium.base.ThreadUtils;
import org.chromium.components.payments.PaymentApp;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentAppServiceBridge$PaymentAppServiceCallback {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1460Xy0 f10744a;
    public final /* synthetic */ C2446ez0 b;

    public PaymentAppServiceBridge$PaymentAppServiceCallback(C2446ez0 ez0, AbstractC1460Xy0 xy0, AbstractC2275dz0 dz0) {
        this.b = ez0;
        this.f10744a = xy0;
    }

    public final void onCanMakePaymentCalculated(boolean z) {
        Object obj = ThreadUtils.f10596a;
        this.f10744a.i(z);
    }

    public final void onDoneCreatingPaymentApps() {
        Object obj = ThreadUtils.f10596a;
        this.f10744a.a(this.b);
    }

    public final void onPaymentAppCreated(PaymentApp paymentApp) {
        Object obj = ThreadUtils.f10596a;
        this.f10744a.j(paymentApp);
    }

    public final void onPaymentAppCreationError(String str) {
        Object obj = ThreadUtils.f10596a;
        this.f10744a.k(str);
    }
}
