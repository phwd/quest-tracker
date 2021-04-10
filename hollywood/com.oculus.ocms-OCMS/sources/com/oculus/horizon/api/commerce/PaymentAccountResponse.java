package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.PaymentAccount;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class PaymentAccountResponse implements ValidatableApiResponse {
    public PaymentAccount payment_account;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.payment_account == null) {
            throw new NullPointerException("UserToPaymentAccountResponse had no payment_account");
        }
    }
}
