package com.oculus.horizon.api.commerce;

import com.oculus.horizon.api.common.Entitlement;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class RefundResponse implements ValidatableApiResponse {
    public final Entitlement entitlement;

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        if (this.entitlement == null) {
            throw new NullPointerException("ItemRequestForRefundResponsePayload had no entitlement");
        }
    }
}
