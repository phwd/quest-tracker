package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GetPartyInfoWithMembersResponse {
    public final String id;
    public final int max_size;
    public final BlockedUsers party_blocked_users;
    @Nullable
    public final PartyInviteActivity party_invite_activity;
    public final SocialUser party_leader;
    public final PartyUsers party_users;

    public static class ActivityImage {
        public final String uri;
    }

    public static class Application {
        public final String id;
        public final String package_name;
    }

    public static class BlockedUsers {
        public final Integer count;
    }

    public static class PartyInviteActivity {
        public final Application application;
        public final String deeplink;
        public final String id;
        public final ActivityImage image;
        public final String subtitle;
        public final String title;
    }

    public static class PartyUsers {
        public final Integer count;
        public final ArrayList<SocialUser> nodes;
    }
}
