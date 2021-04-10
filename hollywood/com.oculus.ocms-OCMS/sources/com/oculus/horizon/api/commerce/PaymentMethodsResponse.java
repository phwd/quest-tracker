package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.PaymentMethod;
import com.oculus.horizon.api.common.PaymentMethods;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class PaymentMethodsResponse implements ValidatableApiResponse {
    public PaymentMethods all_payment_methods;
    public PaymentMethod default_digital_payment_method;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        PaymentMethods paymentMethods = this.all_payment_methods;
        if (paymentMethods == null) {
            throw new NullPointerException("UserToAllPaymentMethodsResponse had no all_payment_methods");
        } else if (paymentMethods.nodes == null) {
            throw new NullPointerException("all_payment_methods had no nodes");
        }
    }
}
