package com.oculus.horizon.api.social;

import com.oculus.horizon.api.social.PartyInfoResponse;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class JoinablePartiesResponse {
    public JoinableParties joinable_parties_from_friends;

    public static class BlockedUsers {
        public Integer count;
    }

    public static class JoinableParties {
        public int count;
        public ArrayList<JoinableParty> nodes;
    }

    public static class JoinableParty {
        public String id;
        public int max_size;
        public BlockedUsers party_blocked_users;
        public PartyInfoResponse.PartyUsers party_users;
    }
}
