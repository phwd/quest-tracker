package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInvitesResponse {
    @Nullable
    public InvitedParties invited_parties;

    public static class BlockedUsers {
        public Integer count;
    }

    public static class InvitedParties {
        public ArrayList<Party> nodes;
    }

    public static class Party {
        public String id;
        public SocialUser invited_by;
        public String join_policy;
        public Integer max_size;
        public BlockedUsers party_blocked_users;
        public PartyUsers party_users;
    }

    public static class PartyUsers {
        public Integer count;
        public ArrayList<SocialUser> nodes;
    }
}
