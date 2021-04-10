package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.ExternalCreditCards;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class ExternalCreditCardsResponse implements ValidatableApiResponse {
    public ExternalCreditCards payment_methods;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        ExternalCreditCards externalCreditCards = this.payment_methods;
        if (externalCreditCards == null) {
            throw new NullPointerException("ExternalCreditCardsResponse had no payment_methods");
        } else if (externalCreditCards.nodes == null) {
            throw new NullPointerException("payment_methods had no nodes");
        }
    }
}
