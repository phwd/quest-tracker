package org.chromium.components.payments;

import java.nio.ByteBuffer;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface PaymentRequestUpdateEventListener {
    boolean c(C1033Qy0 qy0);

    boolean changePaymentMethodFromInvokedApp(String str, String str2);

    boolean changeShippingAddress(ByteBuffer byteBuffer);

    boolean changeShippingOptionFromInvokedApp(String str);
}
