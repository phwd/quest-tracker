package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInfoResponse {
    @Nullable
    public CurrentParty current_party;

    public static class Application {
        public String display_name;
        public IconImage icon_image;
        public String id;
        public int max_group_launch_capacity;
        public String package_name;
    }

    public static class BlockedUser {
        public String alias;
        public String id;
    }

    public static class CurrentParty {
        public boolean has_active_link_sharing;
        public String id;
        public InvitedUsers invited_users;
        public String join_policy;
        public int max_size;
        public boolean only_quest_users;
        public PartyBlockedUsers party_blocked_invited_users;
        public PartyBlockedUsers party_blocked_users;
        public GroupLaunchInfo party_group_launch;
        public PartyInviteActivity party_invite_activity;
        public SocialUser party_leader;
        public String party_type;
        public PartyUsers party_users;
    }

    public static class IconImage {
        public String uri;
    }

    public static class InvitedUsers {
        public Integer count;
        public ArrayList<InvitedUsersEdge> edges;
    }

    public static class InvitedUsersEdge {
        public SocialUser node;
        public String sender;
    }

    public static class PartyBlockedUsers {
        public ArrayList<BlockedUser> nodes;
    }

    public static class PartyInviteActivity {
        public Application application;
        public String deeplink;
        public boolean does_activity_support_group_launch;
        public String id;
        public String title;
    }

    public static class PartyUsers {
        public Integer count;
        public ArrayList<SocialUser> nodes;
    }
}
