package org.chromium.components.payments;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class PaymentRequestUpdateEventListener$$CC implements PaymentRequestUpdateEventListener {
    public static boolean changeShippingAddress$$dflt$$(PaymentRequestUpdateEventListener paymentRequestUpdateEventListener, ByteBuffer byteBuffer) {
        CC[] ccArr = C1033Qy0.b;
        return paymentRequestUpdateEventListener.c(C1033Qy0.d(new C4709sD(new C2740gj0(byteBuffer, new ArrayList()))));
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public boolean changeShippingAddress(ByteBuffer byteBuffer) {
        return changeShippingAddress$$dflt$$(this, byteBuffer);
    }
}
