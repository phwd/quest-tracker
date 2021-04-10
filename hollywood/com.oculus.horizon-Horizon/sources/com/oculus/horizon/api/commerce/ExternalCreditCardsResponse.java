package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.ExternalCreditCards;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class ExternalCreditCardsResponse implements ValidatableApiResponse {
    public final ExternalCreditCards payment_methods;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        ExternalCreditCards externalCreditCards = this.payment_methods;
        if (externalCreditCards == null) {
            str = "ExternalCreditCardsResponse had no payment_methods";
        } else if (externalCreditCards.nodes == null) {
            str = "payment_methods had no nodes";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
