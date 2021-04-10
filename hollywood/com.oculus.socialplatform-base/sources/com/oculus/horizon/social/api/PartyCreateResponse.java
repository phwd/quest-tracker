package com.oculus.horizon.social.api;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import com.oculus.http.core.base.ValidatableApiResponse;

@SingleEntryMapResponse
public class PartyCreateResponse implements ValidatableApiResponse {
    public final boolean did_create_party;
    public final Party party;

    public static class Party {
        public final String id;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        Party party2 = this.party;
        if (party2 != null) {
            String str = party2.id;
            if (str == null || str.isEmpty()) {
                throw new NullPointerException("Party.id was null or empty");
            }
            return;
        }
        throw new NullPointerException("Party was null");
    }
}
