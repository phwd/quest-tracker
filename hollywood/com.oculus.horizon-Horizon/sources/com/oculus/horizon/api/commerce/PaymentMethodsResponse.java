package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.PaymentMethod;
import com.oculus.horizon.api.common.PaymentMethods;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class PaymentMethodsResponse implements ValidatableApiResponse {
    public final PaymentMethods all_payment_methods;
    public PaymentMethod default_digital_payment_method;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        PaymentMethods paymentMethods = this.all_payment_methods;
        if (paymentMethods == null) {
            str = "UserToAllPaymentMethodsResponse had no all_payment_methods";
        } else if (paymentMethods.nodes == null) {
            str = "all_payment_methods had no nodes";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
