package org.chromium.components.payments;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentHandlerHost {

    /* renamed from: a  reason: collision with root package name */
    public long f10873a;

    public PaymentHandlerHost(WebContents webContents, PaymentRequestUpdateEventListener paymentRequestUpdateEventListener) {
        this.f10873a = N.MNMwlyDN(webContents, paymentRequestUpdateEventListener);
    }

    public long getNativeBridge() {
        return this.f10873a;
    }
}
