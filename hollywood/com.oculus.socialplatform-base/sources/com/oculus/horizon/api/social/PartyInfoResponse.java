package com.oculus.horizon.api.social;

import com.oculus.http.core.annotations.SingleEntryMapResponse;
import java.util.ArrayList;
import javax.annotation.Nullable;

@SingleEntryMapResponse
public class PartyInfoResponse {
    @Nullable
    public final CurrentParty current_party;

    public static class Application {
        public final String display_name;
        public final IconImage icon_image;
        public final String id;
        public final int max_group_launch_capacity;
        public final String package_name;
    }

    public static class BlockedUser {
        public final String alias;
        public final String id;
    }

    public static class CurrentParty {
        public final boolean has_active_link_sharing;
        public final String id;
        public final InvitedUsers invited_users;
        public final String join_policy;
        public final int max_size;
        public final boolean only_quest_users;
        public final PartyBlockedUsers party_blocked_invited_users;
        public final PartyBlockedUsers party_blocked_users;
        public final GroupLaunchInfo party_group_launch;
        public final PartyInviteActivity party_invite_activity;
        public final SocialUser party_leader;
        public final String party_type;
        public final PartyUsers party_users;
    }

    public static class IconImage {
        public final String uri;
    }

    public static class InvitedUsers {
        public Integer count;
        public final ArrayList<InvitedUsersEdge> edges;
    }

    public static class InvitedUsersEdge {
        public final SocialUser node;
        public final String sender;
    }

    public static class PartyBlockedUsers {
        public final ArrayList<BlockedUser> nodes;
    }

    public static class PartyInviteActivity {
        public final Application application;
        public final String deeplink;
        public final boolean does_activity_support_group_launch;
        public final String id;
        public final String title;
    }

    public static class PartyUsers {
        public Integer count;
        public final ArrayList<PartyUsersEdge> edges;
    }

    public static class PartyUsersEdge {
        public final String microphone_channel_selection;
        public final SocialUser node;
    }
}
