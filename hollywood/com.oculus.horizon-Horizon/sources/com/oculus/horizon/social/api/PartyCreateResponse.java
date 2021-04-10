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
        String str;
        Party party2 = this.party;
        if (party2 != null) {
            String str2 = party2.id;
            if (str2 == null || str2.isEmpty()) {
                str = "Party.id was null or empty";
            } else {
                return;
            }
        } else {
            str = "Party was null";
        }
        throw new NullPointerException(str);
    }
}
