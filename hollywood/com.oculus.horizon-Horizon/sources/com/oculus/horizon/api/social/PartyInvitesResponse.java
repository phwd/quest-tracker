package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInvitesResponse {
    @Nullable
    public final InvitedParties invited_parties;

    public static class BlockedUsers {
        public final Integer count;
    }

    public static class InvitedParties {
        public final ArrayList<Party> nodes;
    }

    public static class Party {
        public final String id;
        public final SocialUser invited_by;
        public final String join_policy;
        public final Integer max_size;
        public final BlockedUsers party_blocked_users;
        public final PartyUsers party_users;
    }

    public static class PartyUsers {
        public Integer count;
        public final ArrayList<SocialUser> nodes;
    }
}
