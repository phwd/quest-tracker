package com.oculus.horizon.social.api;

import X.AnonymousClass006;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class UserGetCurrentPartyResponse {
    public final Party current_party;

    public static class Party {
        public final String id;
    }

    public String toString() {
        Party party = this.current_party;
        if (party != null) {
            return AnonymousClass006.A05("Party with party id: ", party.id);
        }
        return "Party is null";
    }
}
