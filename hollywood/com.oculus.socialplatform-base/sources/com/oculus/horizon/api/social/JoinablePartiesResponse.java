package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;

@SingleEntryMapResponse
public class JoinablePartiesResponse {
    public final JoinableParties joinable_parties_from_friends;

    public static class BlockedUsers {
        public final Integer count;
    }

    public static class JoinableParties {
        public final int count;
        public final ArrayList<JoinableParty> nodes;
    }

    public static class JoinableParty {
        public final String id;
        public final int max_size;
        public final BlockedUsers party_blocked_users;
        public final PartyUsers party_users;
    }

    public static class PartyUsers {
        public final Integer count;
        public final ArrayList<SocialUser> nodes;
    }
}
