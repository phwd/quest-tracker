package com.oculus.horizon.parties.api;

import com.oculus.horizon.api.common.Party;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class PartyStatusResponse {
    public User user;

    public static class User {
        public Party current_party;
    }
}
