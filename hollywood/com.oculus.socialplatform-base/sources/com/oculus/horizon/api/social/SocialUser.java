package com.oculus.horizon.api.social;

import com.oculus.horizon.api.common.user.ProfilePhoto;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class SocialUser {
    public final String alias;
    public final boolean can_viewer_message;
    @Nullable
    public final CurrentParty current_party;
    public final String friend_status;
    public final String id;
    public final boolean is_blocked_by_viewer;
    public final boolean is_in_room;
    @Nullable
    public final Presence most_recent_presence;
    @Nullable
    public final String mutual_context_string;
    public final String name;
    @Nullable
    public final ArrayList<SocialUser> people_nearby;
    @Nullable
    public final String presence;
    public final String presence_status;
    public final ProfilePhoto profile_photo;
    @Nullable
    public final String user_id;

    public class CurrentParty {
        public final String id;
        public final String join_policy;
        public final String max_size;
        public final PartyUsers party_users;

        public CurrentParty() {
        }
    }

    public class PartyUsers {
        public final String count;

        public PartyUsers() {
        }
    }

    public class Presence {
        public final String deeplink_message_for_deeplink_target;
        public final String destination_api_name;
        @Nullable
        public final String last_active_description;
        public final String scoped_user_id;
        public final ArrayList<SocialDeeplinkTarget> vr_apps_for_deeplink_target;

        public Presence() {
        }
    }
}
