package com.oculus.horizon.social.api;

import com.oculus.horizon.api.common.user.User;
import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInvitableUsersResponse {
    @Nullable
    public final Party current_party;
    public final Friends friends;

    public static class Friends {
        public final int count;
        public final ArrayList<User> nodes;
    }

    public static class Party {
        public final String id;
        public final Friends party_invited_users;
        public final Friends party_users;
    }
}
