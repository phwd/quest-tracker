package com.oculus.horizon.api.social;

import com.oculus.horizon.api.common.user.ProfilePhoto;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class SocialUser {
    public String alias;
    public boolean can_viewer_message;
    @Nullable
    public CurrentParty current_party;
    public String friend_status;
    public String id;
    public boolean is_blocked_by_viewer;
    public boolean is_in_room;
    @Nullable
    public Presence most_recent_presence;
    @Nullable
    public String mutual_context_string;
    public String name;
    @Nullable
    public ArrayList<SocialUser> people_nearby;
    @Nullable
    public String presence;
    public String presence_status;
    public ProfilePhoto profile_photo;
    @Nullable
    public String user_id;

    public class Presence {
        public String deeplink_message_for_deeplink_target;
        public String destination_api_name;
        @Nullable
        public String last_active_description;
        public String scoped_user_id;
        public ArrayList<SocialDeeplinkTarget> vr_apps_for_deeplink_target;

        public Presence() {
        }
    }

    public class CurrentParty {
        public String id;
        public String join_policy;
        public String max_size;
        public PartyUsers party_users;

        public CurrentParty() {
        }
    }

    public class PartyUsers {
        public String count;

        public PartyUsers() {
        }
    }
}
