package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class GetPartyInfoWithMembersResponse {
    public String id;
    public int max_size;
    public BlockedUsers party_blocked_users;
    @Nullable
    public PartyInviteActivity party_invite_activity;
    public SocialUser party_leader;
    public PartyUsers party_users;

    public static class ActivityImage {
        public String uri;
    }

    public static class Application {
        public String id;
        public String package_name;
    }

    public static class BlockedUsers {
        public Integer count;
    }

    public static class PartyInviteActivity {
        public Application application;
        public String deeplink;
        public String id;
        public ActivityImage image;
        public String subtitle;
        public String title;
    }

    public static class PartyUsers {
        public Integer count;
        public ArrayList<SocialUser> nodes;
    }
}
